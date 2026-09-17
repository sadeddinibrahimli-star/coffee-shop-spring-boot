package com.coffeeshop.coffee_shop_spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "chat_messages")
public class ChatMessage {
    @Id @GeneratedValue
    Long id;
    String sender;
    String content;
    LocalDateTime timeStamp = LocalDateTime.now();
}
