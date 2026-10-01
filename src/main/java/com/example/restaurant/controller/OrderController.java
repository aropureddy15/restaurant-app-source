package com.example.restaurant.controller;

import com.example.restaurant.entity.CustomerOrder;
import com.example.restaurant.repository.OrderRepository;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderRepository repo;
    public OrderController(OrderRepository repo){this.repo=repo;}

    @GetMapping public List<CustomerOrder> all(){return repo.findAll();}
    @GetMapping("/{id}") public ResponseEntity<CustomerOrder> one(@PathVariable Long id){
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping public ResponseEntity<CustomerOrder> create(@RequestBody CustomerOrder order){
        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(order));
    }
    @PatchMapping("/{id}/status") public ResponseEntity<CustomerOrder> status(@PathVariable Long id,@RequestParam String value){
        return repo.findById(id).map(o -> {o.setStatus(value.toUpperCase()); return ResponseEntity.ok(repo.save(o));})
                .orElse(ResponseEntity.notFound().build());
    }
}
