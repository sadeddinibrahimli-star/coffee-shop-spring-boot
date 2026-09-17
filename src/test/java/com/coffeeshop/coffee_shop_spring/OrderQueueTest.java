package com.coffeeshop.coffee_shop_spring;

import com.coffeeshop.coffee_shop_spring.model.Order;
import com.coffeeshop.coffee_shop_spring.queue.OrderQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderQueueTest {
    private OrderQueue orderQueue;

    @BeforeEach
    void setUp() {
        orderQueue = new OrderQueue();
    }

    @Test
    void testAddOrder() throws InterruptedException {
        Order order = new Order();
        order.setCoffee("Cappuccino");
        order.setCost(2.99);
        orderQueue.addOrder(order);
        assertEquals(1, orderQueue.size());
    }

    @Test
    void testIsEmpty() throws InterruptedException {
        assertTrue(orderQueue.isEmpty());
        orderQueue.addOrder(new Order());
        assertFalse(orderQueue.isEmpty());
    }

    @Test
    void testTakeOrder() throws InterruptedException {
        Order order = new Order();
        order.setCoffee("Espresso");
        orderQueue.addOrder(order);
        Order taken = orderQueue.takeOrder();
        assertEquals("Espresso", taken.getCoffee());
        assertTrue(orderQueue.isEmpty());
    }
}
