package by.samsonnik.shopSpringApp.demo.dao;

import by.samsonnik.shopSpringApp.demo.models.Order;
import by.samsonnik.shopSpringApp.demo.repositories.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDao {

    private final OrderRepository orderRepository;

    public OrderDao(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }
}
