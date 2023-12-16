package by.samsonnik.shopSpringApp.demo.controllers;

import by.samsonnik.shopSpringApp.demo.dao.ProductDao;
import by.samsonnik.shopSpringApp.demo.exception.OutOfStockException;
import by.samsonnik.shopSpringApp.demo.models.DefaultCartService;
import by.samsonnik.shopSpringApp.demo.models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    private final ProductDao productDao;
    private final DefaultCartService cartService;

    public ProductController(ProductDao productDao, DefaultCartService cartService) {
        this.productDao = productDao;
        this.cartService = cartService;
    }

    @GetMapping("/products/{id}")
    public String getProductById(Model model, @PathVariable("id") String id) {
        model.addAttribute("product", productDao.findProductById(Integer.parseInt(id)));
        return "product";
    }

    @GetMapping("/products")
    public String getProductsAndSortByPrice(Model model, @RequestParam(required = false, name = "sort") String sort,
                                            @RequestParam(required = false, name = "query") String query) {
        List<Product> data = new ArrayList<>();
        if (sort == null) {
            data = productDao.findAllProducts();
        } else {
            if (sort.equals("increase")) {
                data = productDao.findAllProductsAndSortByPriceIncrease();
            }
            if (sort.equals("decrease")) {
                data = productDao.findAllProductsAndSortByPriceDecrease();
            }
        }
        if (query != null) {
            data = productDao.findAllProductsByName(query);
        }
        model.addAttribute("products", data);
        return "products";
    }

    @GetMapping("/products/add")
    public String addItemToCart(@RequestParam(value = "productId") String productId,
                                @RequestParam(value = "productQuantity") String productQuantity, Model model) {
        Product product = productDao.findProductById(Integer.parseInt(productId));
        try {
            cartService.add(cartService.getCart(), Integer.parseInt(productId), Integer.parseInt(productQuantity));
        } catch (OutOfStockException exception) {
            model.addAttribute("error", "Products available " + exception.getStockAvailable() +
                    ", requested " + exception.getStockRequested());
            return getProductById(model.addAttribute("product", product), String.valueOf(product.getId()));
        }
        model.addAttribute("success", "product added");
        return getProductById(model.addAttribute("product", product), String.valueOf(product.getId()));
    }
}
