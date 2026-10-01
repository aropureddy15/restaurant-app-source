package com.example.restaurant.repository;
import com.example.restaurant.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderRepository extends JpaRepository<CustomerOrder, Long> {}
