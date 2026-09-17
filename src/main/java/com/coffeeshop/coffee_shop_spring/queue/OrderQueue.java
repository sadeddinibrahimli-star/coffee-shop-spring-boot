package com.coffeeshop.coffee_shop_spring.queue;

import com.coffeeshop.coffee_shop_spring.model.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class OrderQueue {
    BlockingQueue<Order> queue = new LinkedBlockingQueue<>();
    public void addOrder(Order order) throws InterruptedException  {
        queue.put(order);
    }
    public Order takeOrder() throws InterruptedException {
        return queue.take();
    }
    public int size(){
        return queue.size();
    }
    public boolean isEmpty(){
        return queue.isEmpty();
    }
}
