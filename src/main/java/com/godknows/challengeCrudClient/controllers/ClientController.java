package com.godknows.challengeCrudClient.controllers;


import com.godknows.challengeCrudClient.dto.ClientDTO;
import com.godknows.challengeCrudClient.entities.Client;
import com.godknows.challengeCrudClient.repositories.ClientRepository;
import com.godknows.challengeCrudClient.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping(value="/clients")
public class ClientController {

    @Autowired
    private ClientService cliService;

    @GetMapping(value="/{id}")
    public ResponseEntity<ClientDTO> findById (@PathVariable Long id){
        ClientDTO dto = cliService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAll(Pageable pageable){
        Page<ClientDTO> dto = cliService.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> insert (@Valid @RequestBody ClientDTO dto){
        dto = cliService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<ClientDTO> update (@PathVariable Long id, @Valid @RequestBody ClientDTO dto){
        dto = cliService.update(id, dto);
        return ResponseEntity.ok(dto);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        cliService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
