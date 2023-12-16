package by.samsonnik.shopSpringApp.demo.service;

import by.samsonnik.shopSpringApp.demo.dao.ClientDao;
import by.samsonnik.shopSpringApp.demo.models.Client;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

    private final ClientDao clientDao;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(ClientDao clientDao, PasswordEncoder passwordEncoder) {
        this.clientDao = clientDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(Client client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        clientDao.saveClient(client);
    }
}
