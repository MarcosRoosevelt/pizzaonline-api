package com.victor.pizzaonline.repository;

import com.victor.pizzaonline.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
