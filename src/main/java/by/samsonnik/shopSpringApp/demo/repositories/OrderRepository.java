package by.samsonnik.shopSpringApp.demo.repositories;

import by.samsonnik.shopSpringApp.demo.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
