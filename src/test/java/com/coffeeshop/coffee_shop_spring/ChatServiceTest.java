package com.coffeeshop.coffee_shop_spring;

import com.coffeeshop.coffee_shop_spring.model.ChatMessage;
import com.coffeeshop.coffee_shop_spring.repository.ChatRepository;
import com.coffeeshop.coffee_shop_spring.service.ChatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ChatServiceTest {

    @Mock
    private ChatRepository chatRepository;

    @InjectMocks
    private ChatService chatService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendMessage() {
        ChatMessage savedMessage = new ChatMessage();
        savedMessage.setSender("Customer");
        savedMessage.setContent("Hello!");

        when(chatRepository.save(any(ChatMessage.class))).thenReturn(savedMessage);

        ChatMessage result = chatService.sendMessage("Customer", "Hello!");
        assertNotNull(result);
        assertEquals("Customer", result.getSender());
        verify(chatRepository, times(1)).save(any(ChatMessage.class));
    }

    @Test
    void testGetAllMessages() {
        ChatMessage msg1 = new ChatMessage();
        msg1.setSender("Customer");
        msg1.setContent("Hello!");

        ChatMessage msg2 = new ChatMessage();
        msg2.setSender("Barista");
        msg2.setContent("Hi there!");

        when(chatRepository.findAll()).thenReturn(List.of(msg1, msg2));

        List<ChatMessage> messages = chatService.getAllMessage();
        assertEquals(2, messages.size());
        verify(chatRepository, times(1)).findAll();
    }

    @Test
    void testGetMessagesBySender() {
        ChatMessage msg = new ChatMessage();
        msg.setSender("Customer");
        msg.setContent("Order please");

        when(chatRepository.findBySender("Customer")).thenReturn(List.of(msg));

        List<ChatMessage> messages = chatService.getMessage("Customer");
        assertEquals(1, messages.size());
        assertEquals("Customer", messages.get(0).getSender());
    }
}
