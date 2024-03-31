package com.victor.pizzaonline.controller;


import com.victor.pizzaonline.domain.cliente.Cliente;
import com.victor.pizzaonline.domain.cliente.ClienteResponseDTO;
import com.victor.pizzaonline.domain.order.Order;
import com.victor.pizzaonline.domain.order.OrderRequestDTO;
import com.victor.pizzaonline.domain.order.OrderResponseDTO;
import com.victor.pizzaonline.domain.pizza.Pizza;
import com.victor.pizzaonline.domain.pizza.PizzaResponseDTO;
import com.victor.pizzaonline.repository.ClienteRepository;
import com.victor.pizzaonline.repository.FlavorRepository;
import com.victor.pizzaonline.repository.OrderRepository;
import com.victor.pizzaonline.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Security;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final PizzaRepository pizzaRepository;
    @Autowired
    private final ClienteRepository clienteRepository;
    @Autowired
    private final FlavorRepository flavorRepository;

    public OrderController(OrderRepository orderRepository, PizzaRepository pizzaRepository, ClienteRepository clienteRepository, FlavorRepository flavorRepository) {
        this.orderRepository = orderRepository;
        this.pizzaRepository = pizzaRepository;
        this.clienteRepository = clienteRepository;
        this.flavorRepository = flavorRepository;
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody OrderRequestDTO orderRequestDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Cliente cliente = clienteRepository.findClienteByEmail(email);
        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }

        List<Pizza> pizzas = orderRequestDTO.pizzas();
        if (pizzas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nenhuma pizza encontrada");
        }

        List<Pizza> savedPizzas = pizzaRepository.saveAll(pizzas);

        double totalValue = savedPizzas.stream().mapToDouble(Pizza::getPrice).sum();

        Order order = new Order(totalValue, LocalDateTime.now(), cliente, savedPizzas);
        orderRepository.save(order);

        ClienteResponseDTO clienteDTO = new ClienteResponseDTO(cliente.getId(), cliente.getName(), cliente.getEmail(), cliente.getAddress());
        List<PizzaResponseDTO> pizzasDTO = savedPizzas.stream()
                .map(pizza -> new PizzaResponseDTO(pizza.getId(), pizza.getName(), pizza.getPrice(), pizza.getFlavors()))
                .collect(Collectors.toList());

        OrderResponseDTO orderResponseDTO = new OrderResponseDTO(order.getId(), order.getTotalValue(),
                order.getOrderDate(), clienteDTO, pizzasDTO);

        return ResponseEntity.ok().body(orderResponseDTO);
    }

    @GetMapping("/cliente")
    public List<OrderResponseDTO> getALl(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Cliente cliente = clienteRepository.findClienteByEmail(email);
        List<Order> orders = cliente.getOrders();

        return orders.stream()
                .map(order -> {
                    ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO(order.getCliente().getId(), order.getCliente().getName(), order.getCliente().getEmail(),order.getCliente().getAddress());
                    List<PizzaResponseDTO> pizzaResponseDTOS = order.getPizzas().stream()
                            .map(pizza -> new PizzaResponseDTO(pizza.getId(), pizza.getName(), pizza.getPrice(), pizza.getFlavors())).toList();
                    return new OrderResponseDTO(order.getId(), order.getTotalValue(), order.getOrderDate(), clienteResponseDTO, pizzaResponseDTOS);
                }).toList();
    }
}
