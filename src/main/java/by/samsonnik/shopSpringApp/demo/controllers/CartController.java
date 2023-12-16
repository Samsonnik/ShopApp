package by.samsonnik.shopSpringApp.demo.controllers;

import by.samsonnik.shopSpringApp.demo.models.DefaultCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {

    private DefaultCartService cartService;

    public CartController(DefaultCartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/products/cartList")
    public String getCartList(Model model) {
        model.addAttribute("cartItems", cartService.getCart().getCartItems());
        model.addAttribute("totalPrice", cartService.getCart().getTotalPrice());
        return "cartList";
    }

    @GetMapping("/products/cartList/delete")
    public String deleteItemFromCart(@RequestParam(value = "productId") String productId, Model model) {
        cartService.delete(cartService.getCart(), Integer.parseInt(productId));
        return getCartList(model);
    }
}
