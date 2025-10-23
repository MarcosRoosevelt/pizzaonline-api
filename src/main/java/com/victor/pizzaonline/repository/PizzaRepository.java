package com.victor.pizzaonline.repository;

import com.victor.pizzaonline.domain.pizza.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
