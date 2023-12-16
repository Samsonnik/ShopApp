package by.samsonnik.shopSpringApp.demo.service;

import by.samsonnik.shopSpringApp.demo.dao.ClientDao;
import by.samsonnik.shopSpringApp.demo.models.Client;
import by.samsonnik.shopSpringApp.demo.models.DefaultCartService;
import by.samsonnik.shopSpringApp.demo.models.Order;
import by.samsonnik.shopSpringApp.demo.models.OrderDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final DefaultCartService cartService;
    private final CheckSenderService checkSenderService;
    private final ClientDao clientDao;

    public OrderService(DefaultCartService cartService, CheckSenderService checkSenderService, ClientDao clientDao) {
        this.cartService = cartService;
        this.checkSenderService = checkSenderService;
        this.clientDao = clientDao;
    }

    public List<Order> convertDTO(OrderDTO orderDTO) {
        List<Order> orderList = new ArrayList<>();
        cartService.getCart().getCartItems().forEach(cartItem -> {
            Order order = new Order();
            order.setTownToDelivery(orderDTO.getTownToDelivery());
            order.setClientAddress(orderDTO.getClientAddress());
            order.setClientPhone(orderDTO.getClientPhone());
            order.setClientId(getClient().getId());
            order.setProductId(cartItem.getProduct().getId());
            order.setProductNumber(cartItem.getQuantity());
            order.setClientEmail(orderDTO.getClientEmail());
            order.setTotalCost(cartItem.getQuantity() * cartItem.getProduct().getCost());
            orderList.add(order);
            if (orderDTO.isSendCheck()) {
                checkSenderService.sendMail(makeMessageForOrderCheck(order), order.getClientEmail());
            }
        });
        return orderList;
    }

    private Client getClient() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return clientDao.findClientByName(authentication.getName());
    }

    private String makeMessageForOrderCheck(Order order) {
        StringBuilder data = new StringBuilder();
        data.append("You've ordered:\n");
        cartService.getCart().getCartItems()
                .forEach(cartItem -> {
                    data.append(cartItem.getProduct().getName());
                    data.append("\n");
                    data.append("Product number: ");
                    data.append(cartItem.getQuantity());
                    data.append("\n");
                    data.append("Total cost: ");
                    data.append(order.getTotalCost());
                    data.append("\n");
                    data.append("Your email: ");
                    data.append(order.getClientEmail());
                    data.append("\n");
                    data.append("Your address: ");
                    data.append(order.getClientAddress());
                    data.append("\n");
                    data.append("Your town: ");
                    data.append(order.getTownToDelivery());
                    data.append("\n");
                    data.append("Your phone: ");
                    data.append(order.getClientPhone());
                });
        return data.toString();
    }
}
