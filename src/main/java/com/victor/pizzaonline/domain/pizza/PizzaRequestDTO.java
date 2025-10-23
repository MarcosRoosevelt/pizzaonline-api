package com.victor.pizzaonline.domain.pizza;

import com.victor.pizzaonline.domain.flavor.Flavor;

import java.util.List;

public record PizzaRequestDTO(String name, Double price, PizzaType pizzaType, List<Flavor> flavors) {
}
