package com.angular.angular14spring.controller;

import java.util.List;
import com.angular.angular14spring.model.Client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.angular.angular14spring.repository.ClientRepository;


import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/clients")
@AllArgsConstructor
@Component
public class ClientController {

    private final ClientRepository clientRepository;


    @GetMapping
    public List<Client> list() {
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id) {
        return clientRepository.findById(id)
            .map(record -> ResponseEntity.ok().body(record))
            .orElse(ResponseEntity.notFound().build());
    }

    @ResponseStatus(code = HttpStatus.CREATED )
    @PostMapping
    public Client create(@RequestBody Client client) {
       return clientRepository.save(client);
        // return ResponseEntity.status(HttpStatus.CREATED)
        //    .body(clientRepository.save(client));

    }
    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client client)
    {
        return clientRepository.findById(id)
            .map(clientFound -> {
                clientFound.setName(client.getName());
                clientFound.setCpf(client.getCpf());
                clientFound.setEmail(client.getEmail());
                clientFound.setPhone(client.getPhone());
                Client updated = clientRepository.save(clientFound);
                return ResponseEntity.ok().body(updated);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return clientRepository.findById(id)
        .map(clientFound -> {
            clientRepository.deleteById(id);
            return ResponseEntity.noContent().<Void>build();
        })
        .orElse(ResponseEntity.notFound().build());
    }
 }