package com.example.restaurant.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="restaurants")
public class Restaurant {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;
    private String address;
    private String phone;

    public Restaurant() {}
    public Restaurant(String name, String address, String phone) {
        this.name=name; this.address=address; this.phone=phone;
    }
    public Long getId(){return id;}
    public String getName(){return name;}
    public void setName(String name){this.name=name;}
    public String getAddress(){return address;}
    public void setAddress(String address){this.address=address;}
    public String getPhone(){return phone;}
    public void setPhone(String phone){this.phone=phone;}
}
