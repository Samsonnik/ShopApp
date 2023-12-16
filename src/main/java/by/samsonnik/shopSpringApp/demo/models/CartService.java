package by.samsonnik.shopSpringApp.demo.models;

import by.samsonnik.shopSpringApp.demo.exception.OutOfStockException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public interface CartService {
    void add(Cart cart, Integer productId, int quantity) throws OutOfStockException;
    void update(Cart cart, Integer productId, int quantity);
    void delete(Cart cart, Integer productId);
}
