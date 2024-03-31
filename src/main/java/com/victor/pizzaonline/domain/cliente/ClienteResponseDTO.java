package com.victor.pizzaonline.domain.cliente;

import com.victor.pizzaonline.domain.address.Address;

import java.util.UUID;

public record ClienteResponseDTO(UUID id, String name, String email, Address address) {

    public ClienteResponseDTO(UUID id, String name, String email, Address address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }
}
