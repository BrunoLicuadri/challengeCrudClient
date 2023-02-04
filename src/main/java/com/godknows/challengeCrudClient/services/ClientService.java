package com.godknows.challengeCrudClient.services;

import com.godknows.challengeCrudClient.dto.ClientDTO;
import com.godknows.challengeCrudClient.entities.Client;
import com.godknows.challengeCrudClient.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll (Pageable pageable){
        Page<Client> result = cliRepo.findAll(pageable);
        return result.map(x-> new ClientDTO(x));
    }


    @Transactional
    public ClientDTO insert (ClientDTO dto){
        Client entity = new Client();
        copyDtoToEntity(dto, entity);
        entity = cliRepo.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update (Long id, ClientDTO dto){
        Client entity = cliRepo.getReferenceById(id);
        copyDtoToEntity(dto, entity);
        entity = cliRepo.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional
    public void delete (Long id){
        cliRepo.deleteById(id);
    }


    private void copyDtoToEntity (ClientDTO dto, Client entity){
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }

}
