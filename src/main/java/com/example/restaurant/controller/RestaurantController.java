package com.example.restaurant.controller;

import com.example.restaurant.entity.Restaurant;
import com.example.restaurant.repository.RestaurantRepository;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    private final RestaurantRepository repo;
    public RestaurantController(RestaurantRepository repo){this.repo=repo;}

    @GetMapping public List<Restaurant> all(){return repo.findAll();}
    @GetMapping("/{id}") public ResponseEntity<Restaurant> one(@PathVariable Long id){
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping public ResponseEntity<Restaurant> create(@Valid @RequestBody Restaurant r){
        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(r));
    }
    @PutMapping("/{id}") public ResponseEntity<Restaurant> update(@PathVariable Long id,@Valid @RequestBody Restaurant r){
        return repo.findById(id).map(old -> {
            old.setName(r.getName()); old.setAddress(r.getAddress()); old.setPhone(r.getPhone());
            return ResponseEntity.ok(repo.save(old));
        }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){
        if(!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id); return ResponseEntity.noContent().build();
    }
}
