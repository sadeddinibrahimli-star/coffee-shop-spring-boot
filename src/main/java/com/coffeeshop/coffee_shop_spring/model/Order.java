package com.coffeeshop.coffee_shop_spring.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Data
@Table(name = "orderTable")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private String coffee;
    private Double cost;
    private String status = "Pending";
    private LocalDateTime createdAt = LocalDateTime.now();
}
