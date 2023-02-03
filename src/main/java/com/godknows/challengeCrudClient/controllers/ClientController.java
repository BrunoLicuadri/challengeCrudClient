package com.godknows.challengeCrudClient.controllers;


import com.godknows.challengeCrudClient.entities.Client;
import com.godknows.challengeCrudClient.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping(value="/clients")
public class ClientController {

    @Autowired
    private ClientRepository cliRepo;

    @GetMapping
    public String test(){
        Optional<Client> result = cliRepo.findById(1L);
        Client client = result.get();
        return client.getName();
    }


}
