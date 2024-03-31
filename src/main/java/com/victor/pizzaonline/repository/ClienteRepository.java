package com.victor.pizzaonline.repository;

import com.victor.pizzaonline.domain.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    UserDetails findByEmail(String email);
    Cliente findClienteByEmail(String email);
}
