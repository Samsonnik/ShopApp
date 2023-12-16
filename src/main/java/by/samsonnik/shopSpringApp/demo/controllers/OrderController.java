package by.samsonnik.shopSpringApp.demo.controllers;

import by.samsonnik.shopSpringApp.demo.dao.OrderDao;
import by.samsonnik.shopSpringApp.demo.models.*;
import by.samsonnik.shopSpringApp.demo.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.beans.Transient;
import java.util.List;


@Controller
public class OrderController {

    private final OrderService orderService;

    private final OrderDao orderDao;

    public OrderController(OrderService orderService, OrderDao orderDao) {
        this.orderService =orderService;
        this.orderDao =orderDao;
}

    @GetMapping("/orderList")
    public String getOrder(@ModelAttribute("orderDTO") @Valid OrderDTO orderDTO) {
        return "orderList";
    }

    @Transactional
    @PostMapping("/performOrder")
    public String performOrder(@ModelAttribute("orderDTO") @Valid OrderDTO orderDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "orderList";
        }
        List<Order> orders = orderService.convertDTO(orderDTO);
        orders.forEach(orderDao::saveOrder);
        return "redirect:/products";
    }
}
