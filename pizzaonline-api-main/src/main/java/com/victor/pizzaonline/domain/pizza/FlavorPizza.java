package com.victor.pizzaonline.domain.pizza;

import com.victor.pizzaonline.domain.flavor.Flavor;
import jakarta.persistence.*;

@Entity
@Table(name = "flavor_pizza")
public class FlavorPizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;

    @ManyToOne
    @JoinColumn(name = "flavor_id")
    private Flavor flavor;
}
