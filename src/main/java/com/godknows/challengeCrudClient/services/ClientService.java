package com.godknows.challengeCrudClient.services;

import com.godknows.challengeCrudClient.dto.ClientDTO;
import com.godknows.challengeCrudClient.entities.Client;
import com.godknows.challengeCrudClient.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository cliRepo;

    @Transactional(readOnly = true)
    public ClientDTO findById (Long id){
        Optional<Client> result = cliRepo.findById(id);
        Client client = result.get();
        ClientDTO dto = new ClientDTO(client);
        return dto;
    }

}
