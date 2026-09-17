package com.coffeeshop.coffee_shop_spring.command;

import com.coffeeshop.coffee_shop_spring.model.Order;
import com.coffeeshop.coffee_shop_spring.singleton.CoffeeShop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CancelOrderCommand implements OrderCommand {
    final CoffeeShop coffeeShop;
    final Long orderId;
    public CancelOrderCommand( CoffeeShop coffeeShop , Long orderId){
        this.coffeeShop = coffeeShop;
        this.orderId = orderId;
    }
    @Override
    public void execute() {
        List<Order> orders = coffeeShop.getOrders();
        Iterator<Order> iterator = orders.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            Order currentOrder = iterator.next();
            if (currentOrder.getId().equals(orderId)) {
                iterator.remove();
                System.out.println("Order cancelled");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Order not found");
        }
    }
}
