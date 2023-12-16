package by.samsonnik.shopSpringApp.demo.models;

import by.samsonnik.shopSpringApp.demo.dao.ProductDao;
import by.samsonnik.shopSpringApp.demo.exception.OutOfStockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class DefaultCartService implements CartService {

    private Cart cart;
    private ProductDao productDao;

    @Autowired
    public DefaultCartService(Cart cart, ProductDao productDao) {
        this.cart = cart;
        this.productDao = productDao;
    }

    @Override
    @Transactional
    public void add(Cart cart, Integer productId, int quantity) throws OutOfStockException {
        Product product = productDao.findProductById(productId);
            if (quantity > product.getNumberOnTheStore()) {
                throw new OutOfStockException(product, product.getNumberOnTheStore(), quantity);
            }
        Optional<CartItem> cartItemFromCart = cart.getCartItems()
                .stream()
                .filter(cartItem -> cartItem.getProduct().getId() == productId)
                .findFirst();
        if (cartItemFromCart.isPresent()) {
            int currentQuantity = cartItemFromCart.get().getQuantity();
            int totalQuantity = currentQuantity + quantity;
            update(cart, productId, totalQuantity);
            recalculateNumberOnTheStore(productId, quantity);
        } else {
            cart.getCartItems().add(new CartItem(productDao.findProductById(productId), quantity));
            recalculateNumberOnTheStore(productId, quantity);
            recalculatePriceAndQuantity(cart);
        }
    }

    @Override
    @Transactional
    public void update(Cart cart, Integer productId, int quantity) {
        cart.getCartItems()
                .stream()
                .filter(cartItem -> cartItem.getProduct().getId() == productId)
                .findFirst()
                .get()
                .setQuantity(quantity);
        recalculatePriceAndQuantity(cart);
    }

    @Override
    @Transactional
    public void delete(Cart cart, Integer productId) {
        AtomicReference<Integer> quantityFromCart = new AtomicReference<>();
        cart.getCartItems()
                .stream()
                .filter(cartItemFromCart -> cartItemFromCart.getProduct().getId() == productId)
                .findFirst()
                .ifPresent(item -> {
                    quantityFromCart.set(item.getQuantity());
                    cart.getCartItems().remove(item);
                });
        recalculatePriceAndQuantity(cart);
        int addQuantity = productDao.findProductById(productId).getNumberOnTheStore() + quantityFromCart.get();
        productDao.findProductById(productId).setNumberOnTheStore(addQuantity);
    }

    private void recalculateNumberOnTheStore(int productId, int quantity) {
        int currentQuantity = productDao.findProductById(productId).getNumberOnTheStore();
        int newQuantity = currentQuantity - quantity;
        productDao.findProductById(productId).setNumberOnTheStore(newQuantity);
    }

    private void recalculatePriceAndQuantity(Cart cart) {
        cart.setTotalPrice(recalculateTotalPrice(cart.getCartItems()));
        cart.setTotalQuantity(recalculateTotalQuantity(cart.getCartItems()));
    }

    private BigDecimal recalculateTotalPrice(List<CartItem> cartItemList) {
        AtomicLong totalPrice = new AtomicLong();
        cartItemList.forEach(cartItem -> totalPrice.getAndAdd((long) (cartItem.getProduct().getCost() * cartItem.getQuantity())));
        return BigDecimal.valueOf(totalPrice.get());
    }

    private int recalculateTotalQuantity(List<CartItem> cartItemList) {
        AtomicLong totalQuantity = new AtomicLong();
        cartItemList.forEach(cartItem -> totalQuantity.getAndAdd(cartItem.getQuantity()));
        return (int) totalQuantity.get();
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
