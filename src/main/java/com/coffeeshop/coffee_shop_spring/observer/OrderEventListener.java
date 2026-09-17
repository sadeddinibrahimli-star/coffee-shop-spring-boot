package com.coffeeshop.coffee_shop_spring.observer;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventListener {
    @EventListener
    void handleOrderReady(OrderReadyEvent event){
        System.out.println("Order Ready: " + event.getMessage());
    }
}
