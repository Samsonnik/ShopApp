package by.samsonnik.shopSpringApp.demo.repositories;

import by.samsonnik.shopSpringApp.demo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByNameContainingIgnoreCase(String query);
}
