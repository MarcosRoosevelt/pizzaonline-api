package com.victor.pizzaonline.controller;


import com.victor.pizzaonline.domain.cliente.Cliente;
import com.victor.pizzaonline.domain.cliente.ClienteResponseDTO;
import com.victor.pizzaonline.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public ClienteResponseDTO getOne(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Cliente cliente = clienteRepository.findClienteByEmail(email);
        return new ClienteResponseDTO(cliente.getId(), cliente.getName(), cliente.getEmail(), cliente.getAddress());
    }
}
