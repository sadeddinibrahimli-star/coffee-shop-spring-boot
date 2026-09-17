package com.coffeeshop.coffee_shop_spring.prototype;

import com.coffeeshop.coffee_shop_spring.model.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PrototypeOrder {
    Order order;
    Order newOrder;
    public void setOrder(Order order){
        this.order = order;
        Order cloned = new Order();
        cloned.setCustomerName(order.getCustomerName());
        cloned.setCoffee(order.getCoffee());
        cloned.setCost(order.getCost());
        cloned.setStatus(order.getStatus());
        this.newOrder = cloned;
    }
   public Order cloneOrder(){
        return newOrder;
    }
}
