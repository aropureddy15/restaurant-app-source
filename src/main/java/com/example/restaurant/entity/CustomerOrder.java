package com.example.restaurant.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="customer_orders")
public class CustomerOrder {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String customerPhone;
    private String itemName;
    private Integer quantity;
    @Column(precision=10, scale=2)
    private BigDecimal totalAmount;
    private String status = "PLACED";
    private LocalDateTime createdAt = LocalDateTime.now();

    public CustomerOrder() {}
    public Long getId(){return id;}
    public String getCustomerName(){return customerName;}
    public void setCustomerName(String v){customerName=v;}
    public String getCustomerPhone(){return customerPhone;}
    public void setCustomerPhone(String v){customerPhone=v;}
    public String getItemName(){return itemName;}
    public void setItemName(String v){itemName=v;}
    public Integer getQuantity(){return quantity;}
    public void setQuantity(Integer v){quantity=v;}
    public BigDecimal getTotalAmount(){return totalAmount;}
    public void setTotalAmount(BigDecimal v){totalAmount=v;}
    public String getStatus(){return status;}
    public void setStatus(String v){status=v;}
    public LocalDateTime getCreatedAt(){return createdAt;}
}
