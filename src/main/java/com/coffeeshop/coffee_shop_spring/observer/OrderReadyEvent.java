package com.coffeeshop.coffee_shop_spring.observer;

import com.coffeeshop.coffee_shop_spring.model.Order;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;
import org.springframework.context.ApplicationEvent;
@Getter
@Setter
public class OrderReadyEvent extends ApplicationEvent {
    Order order;
    String message;
    public OrderReadyEvent(Order order, String message){
        super(order);
        this.order = order;
        this.message = message;
    }
}
