package com.godknows.challengeCrudClient.controllers;


import com.godknows.challengeCrudClient.dto.ClientDTO;
import com.godknows.challengeCrudClient.entities.Client;
import com.godknows.challengeCrudClient.repositories.ClientRepository;
import com.godknows.challengeCrudClient.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping(value="/clients")
public class ClientController {

    @Autowired
    private ClientService cliService;

    @GetMapping(value="/{id}")
    public ClientDTO dto (@PathVariable Long id){
        ClientDTO dto = cliService.findById(id);
        return dto;
    }


}
