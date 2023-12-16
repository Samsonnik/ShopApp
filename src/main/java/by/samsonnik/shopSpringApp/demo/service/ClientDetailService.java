package by.samsonnik.shopSpringApp.demo.service;

import by.samsonnik.shopSpringApp.demo.dao.ClientDao;
import by.samsonnik.shopSpringApp.demo.models.Client;
import by.samsonnik.shopSpringApp.demo.security.ClientDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClientDetailService implements UserDetailsService {

    private final ClientDao clientDao;

    public ClientDetailService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientDao.findClientByName(username);
        if (client == null) {
            throw new UsernameNotFoundException("Your user isn't exist");
        }
        return new ClientDetails(client);
    }
}
