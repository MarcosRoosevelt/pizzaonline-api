package com.victor.pizzaonline.domain.cliente;

import com.victor.pizzaonline.domain.address.Address;

public record RegisterDTO(String name, String phoneNumber, String email, String password, UserRole role, Address address) {
}
