package by.savitskaya.service;

import by.savitskaya.model.Client;
import by.savitskaya.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
@Service
public class ClientServiceImp implements ClientService{
    @Autowired
    private ClientRepository repository;

    public Map<Integer, Client> getAllClient(){
        List<Client> clients = repository.findAll();
        Map<Integer, Client> clientList = new ConcurrentHashMap<>();

        for (Iterator iterator = clients.iterator(); iterator.hasNext(); ) {
            Client client = (Client) iterator.next();
            clientList.put(client.getId(), client);
        }

        return clientList;
    }

    public void addAndEditClient(Client client){
        if (client.getId() == null){
            client.setId(getAllClient().size() + 1);
            repository.save(client);
        } else{
            Optional<Client> clientByEdit = getById(client.getId());
            if (clientByEdit.isPresent()){
                Client editClient = clientByEdit.get();
                String firstName = null;
                String lastName = null;
                Integer age = 0;
                String nameCompany = null;

                firstName = client.getFirstName();
                if (firstName != null){
                    editClient.setFirstName(firstName);
                }
                lastName = client.getLastName();
                if (lastName != null){
                    editClient.setLastName(lastName);
                }
                age = Integer.parseInt(client.getAge());
                if (age != 0){
                    editClient.setAge(String.valueOf(age));
                }
                nameCompany = client.getNameCompany();
                if (nameCompany != null){
                    editClient.setNameCompany(nameCompany);
                }

                repository.save(editClient);
            }
        }


    }

    public Optional<Client> getById(Integer id){
        return repository.findById(id);
    }

    public void deleteClient(Integer id){
        Optional<Client> deleteClient = getById(id);
        if (deleteClient.isPresent()){
          repository.delete(deleteClient.get());
        }
    }


}

