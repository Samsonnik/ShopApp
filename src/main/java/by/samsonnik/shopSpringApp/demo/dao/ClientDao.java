package by.samsonnik.shopSpringApp.demo.dao;

import by.samsonnik.shopSpringApp.demo.models.Client;
import by.samsonnik.shopSpringApp.demo.repositories.ClientRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientDao {

    private final ClientRepository clientRepository;


    public ClientDao(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public Client findClientByName(String name) {
        return clientRepository.findByFirstName(name);
    }

    public void saveClient(Client client) {
        clientRepository.save(client);
    }
}
