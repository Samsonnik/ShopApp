package by.samsonnik.shopSpringApp.demo.models;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@SessionScope
public class Cart {

    private List<CartItem> cartItems = new ArrayList<>();
    private int totalQuantity;
    private BigDecimal totalPrice;

    public Cart() {
    }

    public Cart(int totalQuantity, BigDecimal totalPrice) {
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return totalQuantity == cart.totalQuantity && Objects.equals(cartItems, cart.cartItems) && Objects.equals(totalPrice, cart.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartItems, totalQuantity, totalPrice);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartItems=" + cartItems +
                ", totalQuantity=" + totalQuantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
