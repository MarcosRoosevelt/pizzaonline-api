package com.victor.pizzaonline.repository;

import com.victor.pizzaonline.domain.flavor.Flavor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlavorRepository extends JpaRepository<Flavor, Long> {
    Flavor findByName(String name);

    @Query(value = "SELECT f.name, COUNT(pf.pizza_id) AS numPizzas " +
            "FROM flavors f " +
            "JOIN pizza_flavors pf ON f.id = pf.flavor_id " +
            "GROUP BY f.name " +
            "ORDER BY numPizzas DESC " +
            "LIMIT 5", nativeQuery = true)
    List<Object[]> findTopPopularFlavors();
}
