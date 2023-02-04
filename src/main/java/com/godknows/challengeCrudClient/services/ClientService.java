package com.godknows.challengeCrudClient.services;

import com.godknows.challengeCrudClient.dto.ClientDTO;
import com.godknows.challengeCrudClient.entities.Client;
import com.godknows.challengeCrudClient.repositories.ClientRepository;
import com.godknows.challengeCrudClient.services.exceptions.DatabaseException;
import com.godknows.challengeCrudClient.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
        Client client = result.orElseThrow(()-> new ResourceNotFoundException ("Recurso não encontrado."));
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
        try{
            Client entity = cliRepo.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = cliRepo.save(entity);
            return new ClientDTO(entity);
        }
        catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado.");
        }
    }

    @Transactional
    public void delete (Long id){
        try {
            cliRepo.deleteById(id);
        }
        catch(EmptyResultDataAccessException e){
           throw new ResourceNotFoundException("Recuso não encontrado");
        }
    }


    private void copyDtoToEntity (ClientDTO dto, Client entity){
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }

}
