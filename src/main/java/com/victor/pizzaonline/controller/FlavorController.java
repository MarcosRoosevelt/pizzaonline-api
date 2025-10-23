package com.victor.pizzaonline.controller;

import com.victor.pizzaonline.domain.flavor.Flavor;
import com.victor.pizzaonline.domain.flavor.FlavorRequestDTO;
import com.victor.pizzaonline.repository.FlavorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/flavors")
public class FlavorController {
    @Autowired
    private final FlavorRepository flavorRepository;

    public FlavorController(FlavorRepository flavorRepository) {
        this.flavorRepository = flavorRepository;
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody FlavorRequestDTO data){
        if (flavorRepository.findByName(data.name()) != null) return ResponseEntity.badRequest().body("Sabor já registrado");
        Flavor flavor = new Flavor(data.name());
        flavorRepository.save(flavor);
        return ResponseEntity.ok().body(flavor);
    }

    @GetMapping
    public List<Flavor> getAll(){
        return flavorRepository.findAll();
    }

    @GetMapping("/ranking")
    public List<Object[]> getTop5PopularFlavors() {
        List<Object[]> results = flavorRepository.findTopPopularFlavors();
        return results.stream().limit(5).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Optional<Flavor> getOne(@PathVariable Long id){
        return flavorRepository.findById(id);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(flavorRepository.findById(id).isEmpty()) {
            return ResponseEntity.badRequest().body("Sabor não encontrado");
        }

        flavorRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
