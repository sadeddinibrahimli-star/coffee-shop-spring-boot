package com.coffeeshop.coffee_shop_spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "customer")
public class Customer {
    @Id @GeneratedValue
    Long id;
    String name;
    String loyaltyTier = "REGULAR";
}
