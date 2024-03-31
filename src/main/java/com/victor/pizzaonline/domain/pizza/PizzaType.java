package com.victor.pizzaonline.domain.pizza;

public enum PizzaType {
    PEQUENA("pequena"),
    MEDIA("media"),
    GRANDE("grande"),
    FAMILIA("familia");

    private String pizzaType;

    PizzaType(String pizzaType) {
        this.pizzaType = pizzaType;
    }

    public static PizzaType fromString(String value) {
        for (PizzaType pizzaType : PizzaType.values()) {
            if (pizzaType.name().equalsIgnoreCase(value)) {
                return pizzaType;
            }
        }
        throw new IllegalArgumentException("Unknown enum value: " + value);
    }
}
