package by.samsonnik.shopSpringApp.demo.dao;

import by.samsonnik.shopSpringApp.demo.models.Product;
import by.samsonnik.shopSpringApp.demo.repositories.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDao {

    private final ProductRepository productRepository;

    public ProductDao(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> findAllProductsAndSortByPriceIncrease() {
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, "cost"));
    }

    public List<Product> findAllProductsAndSortByPriceDecrease() {
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, "cost"));
    }

    public List<Product> findAllProductsByName(String query) {
        return productRepository.findAllByNameContainingIgnoreCase(query);
    }


    public Product findProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public void removeById(int id) {
        productRepository.deleteById(id);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }
}
