package com.victor.pizzaonline.repository;

import com.victor.pizzaonline.domain.flavor.Flavor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlavorRepository extends JpaRepository<Flavor, Long> {
    Flavor findByName(String name);

    @Query("SELECT f.name, COUNT(p) AS numPizzas " +
            "FROM Pizza p " +
            "JOIN p.flavors f " +
            "GROUP BY f.name " +
            "ORDER BY numPizzas DESC " +
            "LIMIT 5")
    List<Object[]> findTopPopularFlavors();
}
