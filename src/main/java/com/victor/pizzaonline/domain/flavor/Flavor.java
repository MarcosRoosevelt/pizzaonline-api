package com.victor.pizzaonline.domain.flavor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "flavors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flavor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Flavor(String name) {
        this.name = name;
    }
}
