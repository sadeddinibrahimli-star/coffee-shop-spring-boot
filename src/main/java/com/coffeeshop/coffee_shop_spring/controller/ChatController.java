package com.coffeeshop.coffee_shop_spring.controller;

import com.coffeeshop.coffee_shop_spring.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/send")
    public Object sendMessage(@RequestBody Map<String, String> payload) {
        String sender = payload.get("sender");
        String content = payload.get("content");
        return chatService.sendMessage(sender, content);
    }

    @GetMapping("/messages/{sender}")
    public Object getMessages(@PathVariable String sender) {
        return chatService.getMessage(sender);
    }

    @GetMapping("/messages")
    public Object getAllMessages() {
        return chatService.getAllMessage();
    }
}