package com.coffeeshop.coffee_shop_spring.singleton;

import com.coffeeshop.coffee_shop_spring.factory.CoffeeFactory;
import com.coffeeshop.coffee_shop_spring.model.Coffee;
import com.coffeeshop.coffee_shop_spring.model.CoffeeType;
import com.coffeeshop.coffee_shop_spring.model.Order;
import com.coffeeshop.coffee_shop_spring.observer.OrderReadyEvent;
import com.coffeeshop.coffee_shop_spring.queue.OrderQueue;
import com.coffeeshop.coffee_shop_spring.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CoffeeShop {
    final CoffeeFactory coffeeFactory;
    final ApplicationEventPublisher publisher;
    final OrderQueue orderQueue;
    final OrderRepository orderRepository;

    @Autowired
    public CoffeeShop(CoffeeFactory coffeeFactory, ApplicationEventPublisher publisher, OrderQueue orderQueue, OrderRepository orderRepository) {
        this.coffeeFactory = coffeeFactory;
        this.publisher = publisher;
        this.orderQueue = orderQueue;
        this.orderRepository = orderRepository;
    }

    public Coffee placeOrder(CoffeeType coffeeType, String customerName) throws InterruptedException {
        Coffee coffee = coffeeFactory.createCoffee(coffeeType);
        Order order = new Order();
        order.setCoffee(coffee.getDescription());
        order.setCost(coffee.getCost());
        order.setCustomerName(customerName);
        orderRepository.save(order);
        publisher.publishEvent(new OrderReadyEvent(order, "Your " + coffee.getDescription() + " is ready!"));
        orderQueue.addOrder(order);
        return coffee;
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
}
