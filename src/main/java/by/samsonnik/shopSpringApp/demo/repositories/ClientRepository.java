package by.samsonnik.shopSpringApp.demo.repositories;

import by.samsonnik.shopSpringApp.demo.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findByFirstName(String name);
}
