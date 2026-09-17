package com.coffeeshop.coffee_shop_spring.repository;

import com.coffeeshop.coffee_shop_spring.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findBySender(String sender);
}
