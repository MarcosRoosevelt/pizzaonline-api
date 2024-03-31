package com.victor.pizzaonline.controller;

import com.victor.pizzaonline.domain.flavor.Flavor;
import com.victor.pizzaonline.domain.pizza.Pizza;
import com.victor.pizzaonline.domain.pizza.PizzaRequestDTO;
import com.victor.pizzaonline.repository.FlavorRepository;
import com.victor.pizzaonline.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {
    @Autowired
    private final PizzaRepository pizzaRepository;
    @Autowired
    private final FlavorRepository flavorRepository;

    public PizzaController(PizzaRepository pizzaRepository, FlavorRepository flavorRepository) {
        this.pizzaRepository = pizzaRepository;
        this.flavorRepository = flavorRepository;
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody PizzaRequestDTO data){
        List<Flavor> flavors = data.flavors();
        Pizza pizza = new Pizza(data.name(), data.price(), data.pizzaType(), flavors);

        pizzaRepository.save(pizza);

        return ResponseEntity.ok().body(pizza);
    }

    @GetMapping("/{id}")
    public Optional<Pizza> getOne(@PathVariable Long id){
        return pizzaRepository.findById(id);
    }
}
