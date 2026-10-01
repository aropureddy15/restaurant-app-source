package com.example.restaurant.controller;

import com.example.restaurant.entity.*;
import com.example.restaurant.repository.*;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    private final MenuItemRepository menu;
    private final RestaurantRepository restaurants;

    public MenuController(MenuItemRepository menu, RestaurantRepository restaurants){
        this.menu=menu; this.restaurants=restaurants;
    }

    @GetMapping public List<MenuItem> all(){return menu.findAll();}
    @GetMapping("/restaurant/{restaurantId}") public List<MenuItem> byRestaurant(@PathVariable Long restaurantId){
        return menu.findByRestaurantId(restaurantId);
    }
    @PostMapping("/restaurant/{restaurantId}")
    public ResponseEntity<?> create(@PathVariable Long restaurantId,@Valid @RequestBody MenuItem item){
        return restaurants.findById(restaurantId).map(r -> {
            item.setRestaurant(r);
            return ResponseEntity.status(HttpStatus.CREATED).body(menu.save(item));
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurant not found"));
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){
        if(!menu.existsById(id)) return ResponseEntity.notFound().build();
        menu.deleteById(id); return ResponseEntity.noContent().build();
    }
}
