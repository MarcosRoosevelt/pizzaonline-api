package com.victor.pizzaonline.domain.address;

import com.victor.pizzaonline.domain.cliente.Cliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "adress")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cep;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Address(String cep, String state, String city, String neighborhood, String street) {
        this.cep = cep;
        this.state = state;
        this.city = city;
        this.neighborhood = neighborhood;
        this.street = street;
    }
}
