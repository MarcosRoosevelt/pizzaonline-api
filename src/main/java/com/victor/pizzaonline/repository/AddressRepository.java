package com.victor.pizzaonline.repository;

import com.victor.pizzaonline.domain.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
