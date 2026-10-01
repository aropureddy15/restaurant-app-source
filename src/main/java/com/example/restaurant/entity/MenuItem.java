package com.example.restaurant.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Entity
@Table(name="menu_items")
public class MenuItem {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private String description;

    @NotNull @PositiveOrZero
    @Column(precision=10, scale=2)
    private BigDecimal price;

    private boolean available = true;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;

    public MenuItem() {}
    public Long getId(){return id;}
    public String getName(){return name;}
    public void setName(String name){this.name=name;}
    public String getDescription(){return description;}
    public void setDescription(String description){this.description=description;}
    public BigDecimal getPrice(){return price;}
    public void setPrice(BigDecimal price){this.price=price;}
    public boolean isAvailable(){return available;}
    public void setAvailable(boolean available){this.available=available;}
    public Restaurant getRestaurant(){return restaurant;}
    public void setRestaurant(Restaurant restaurant){this.restaurant=restaurant;}
}
