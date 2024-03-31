package com.victor.pizzaonline.domain.flavor;

import lombok.Data;

@Data
public class FlavorCount {
    private String flavorName;
    private Long numOrders;

    public FlavorCount(String flavorName, Long numOrders) {
        this.flavorName = flavorName;
        this.numOrders = numOrders;
    }
}
