package com.coffeeshop.coffee_shop_spring.service;

import com.coffeeshop.coffee_shop_spring.model.Order;
import com.coffeeshop.coffee_shop_spring.queue.OrderQueue;
import com.coffeeshop.coffee_shop_spring.singleton.CoffeeShop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class BaristaService {
    final OrderQueue orderQueue;
    final CoffeeShop coffeeShop;
    @Autowired
    BaristaService(OrderQueue orderQueue , CoffeeShop coffeeShop){
        this.coffeeShop = coffeeShop;
        this.orderQueue = orderQueue;
    }
    @Async
    public void processOrders() throws InterruptedException {
        while (true){
            Order order = orderQueue.takeOrder();
            System.out.println("Barista processing: " + order.getCoffee());
            TimeUnit.SECONDS.sleep(2);
            order.setStatus("READY");
            System.out.println("Order ready for: " + order.getCustomerName());
        }
    }
}
