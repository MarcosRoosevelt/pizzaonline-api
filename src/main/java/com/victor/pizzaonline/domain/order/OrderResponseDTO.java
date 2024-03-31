package com.victor.pizzaonline.domain.order;

import com.victor.pizzaonline.domain.cliente.ClienteResponseDTO;
import com.victor.pizzaonline.domain.pizza.PizzaResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDTO(Long id, double totalValue, LocalDateTime orderDate, ClienteResponseDTO clienteResponseDTO, List<PizzaResponseDTO> pizzaResponseDTOS) {
    public OrderResponseDTO(Long id, double totalValue, LocalDateTime orderDate, ClienteResponseDTO clienteResponseDTO, List<PizzaResponseDTO> pizzaResponseDTOS) {
        this.id = id;
        this.totalValue = totalValue;
        this.orderDate = orderDate;
        this.clienteResponseDTO = clienteResponseDTO;
        this.pizzaResponseDTOS = pizzaResponseDTOS;
    }
}
