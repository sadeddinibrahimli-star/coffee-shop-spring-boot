package com.coffeeshop.coffee_shop_spring.websocket;

import com.coffeeshop.coffee_shop_spring.model.ChatMessage;
import com.coffeeshop.coffee_shop_spring.service.ChatService;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final ChatService chatService;
    private final ObjectMapper objectMapper;
    private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    private final Map<String, String> sessionSenders = new ConcurrentHashMap<>();

    @Autowired
    public ChatWebSocketHandler(ChatService chatService) {
        this.chatService = chatService;
        this.objectMapper = JsonMapper.builder().build();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
        System.out.println("WebSocket connected: " + session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
        sessionSenders.remove(session.getId());
        System.out.println("WebSocket disconnected: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        try {
            Map<String, String> payload = objectMapper.readValue(message.getPayload(), Map.class);
            String sender = payload.get("sender");
            String content = payload.get("content");

            // Persist to database
            ChatMessage saved = chatService.sendMessage(sender, content);

            // Broadcast to all connected sessions
            Map<String, Object> response = Map.of(
                "id", saved.getId(),
                "sender", saved.getSender(),
                "content", saved.getContent(),
                "timeStamp", saved.getTimeStamp().toString()
            );
            String responseJson = objectMapper.writeValueAsString(response);

            for (WebSocketSession s : sessions) {
                if (s.isOpen()) {
                    s.sendMessage(new TextMessage(responseJson));
                }
            }
        } catch (Exception e) {
            System.err.println("Error handling WebSocket message: " + e.getMessage());
        }
    }

    public void broadcast(String sender, String content) {
        try {
            Map<String, Object> response = Map.of(
                "sender", sender,
                "content", content,
                "timeStamp", LocalDateTime.now().toString()
            );
            String json = objectMapper.writeValueAsString(response);

            for (WebSocketSession s : sessions) {
                if (s.isOpen()) {
                    s.sendMessage(new TextMessage(json));
                }
            }
        } catch (Exception e) {
            System.err.println("Error broadcasting message: " + e.getMessage());
        }
    }
}
