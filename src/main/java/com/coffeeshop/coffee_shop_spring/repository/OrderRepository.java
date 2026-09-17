package com.coffeeshop.coffee_shop_spring.repository;

import com.coffeeshop.coffee_shop_spring.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(String status);
    List<Order> findByCustomerName(String customerName);
}
