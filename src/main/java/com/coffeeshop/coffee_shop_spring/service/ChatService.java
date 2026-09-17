package com.coffeeshop.coffee_shop_spring.service;

import com.coffeeshop.coffee_shop_spring.model.ChatMessage;
import com.coffeeshop.coffee_shop_spring.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    ChatRepository chatRepository;
    @Autowired
    public ChatService(ChatRepository chatRepository){
        this.chatRepository = chatRepository;
    }
    public ChatMessage sendMessage(String sender , String content){
        ChatMessage message = new ChatMessage();
        message.setSender(sender);
        message.setContent(content);
        return chatRepository.save(message);
    }
    public List<ChatMessage> getMessage(String sender){
        return chatRepository.findBySender(sender);
    }
    public List<ChatMessage> getAllMessage(){
        return chatRepository.findAll();
    }

}
