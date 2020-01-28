package by.savitskaya.service;


import by.savitskaya.model.Client;
import org.springframework.ui.Model;

import java.util.Map;
import java.util.Optional;

public interface ClientService {
    Map<Integer, Client> getAllClient();

    void addAndEditClient(Client client);

    Optional<Client> getById(Integer id);

    void deleteClient(Integer id);


}
