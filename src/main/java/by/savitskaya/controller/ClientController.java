package by.savitskaya.controller;

import by.savitskaya.model.Client;
import by.savitskaya.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping(value = {"/", "/mainPage"})
    public String getMainPage(Model model) {
        model.addAttribute("listClient", clientService.getAllClient());
        return "mainPage";
    }

    @GetMapping(value = "/add")
    public String getAddClientPage(Model model) {
        return "addClient";
    }

    @PostMapping(value = "/add")
    public String saveClient(@ModelAttribute("client") Client client) {
        clientService.addAndEditClient(client);
        return "redirect:/";
    }

    @GetMapping(value = "/edit")
    public String getEditClienPage(Model model, @RequestParam("id") Optional<Integer> id) {
        Optional<Client> editClient = clientService.getById(id.get());
        if (editClient.isPresent()) {
            model.addAttribute("editClient", editClient.get());
        }
        return "editClient";
    }

    @PostMapping(value = "/edit")
    public String editClient(@ModelAttribute("client") Client client){
        clientService.addAndEditClient(client);
        return "redirect:/";
    }

    @GetMapping(value = "/delete")
    public String getDeleteClientPage(Model model, @RequestParam("id") Optional<Integer> id){
        Optional<Client> deleteClient = clientService.getById(id.get());
        if (deleteClient.isPresent()){
            model.addAttribute("deleteClient", deleteClient.get());
        }
        return "deleteClient";
    }

    @PostMapping(value = "delete")
    public String deleteClient(@ModelAttribute("client") Client client){
        clientService.deleteClient(client.getId());
        return "redirect:/";
    }


}
