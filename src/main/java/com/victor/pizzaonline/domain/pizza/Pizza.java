package com.victor.pizzaonline.domain.pizza;


import com.victor.pizzaonline.domain.flavor.Flavor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pizzas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pizza {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private PizzaType pizzaType;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "flavor_pizza",
            joinColumns = { @JoinColumn(name = "pizza_id") },
            inverseJoinColumns = { @JoinColumn(name = "flavor_id") }
    )
    private List<Flavor> flavors = new ArrayList<>();

    public Pizza(String name, Double price, PizzaType pizzaType, List<Flavor> flavors) {
        this.name = name;
        this.price = price;
        this.pizzaType = pizzaType;
        this.flavors = flavors;
    }
}
