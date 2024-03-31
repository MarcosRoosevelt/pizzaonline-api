package com.victor.pizzaonline.domain.order;

import com.victor.pizzaonline.domain.pizza.Pizza;

import java.util.List;

public record OrderRequestDTO(List<Pizza> pizzas) {
}
