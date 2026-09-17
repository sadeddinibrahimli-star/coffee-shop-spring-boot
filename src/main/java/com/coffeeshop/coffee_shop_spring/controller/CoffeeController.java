package com.coffeeshop.coffee_shop_spring.controller;

import com.coffeeshop.coffee_shop_spring.facade.CoffeeShopFacade;
import com.coffeeshop.coffee_shop_spring.model.CoffeeType;
import com.coffeeshop.coffee_shop_spring.model.Order;
import com.coffeeshop.coffee_shop_spring.prototype.PrototypeOrder;
import com.coffeeshop.coffee_shop_spring.strategy.GoldPricing;
import com.coffeeshop.coffee_shop_spring.strategy.PricingStrategy;
import com.coffeeshop.coffee_shop_spring.strategy.RegularPricing;
import com.coffeeshop.coffee_shop_spring.strategy.SilverPricing;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/coffee")
public class CoffeeController {
    private final CoffeeShopFacade facade;
    private final PrototypeOrder prototypeOrder;

    @Autowired
    public CoffeeController(CoffeeShopFacade facade, PrototypeOrder prototypeOrder) {
        this.facade = facade;
        this.prototypeOrder = prototypeOrder;
    }
    @GetMapping("/order/{type}/{customerName}/{pricingType}")
    public Object createOrder(
            @PathVariable String type,
            @PathVariable String customerName,
            @PathVariable String pricingType) throws InterruptedException {

        PricingStrategy strategy = determineStrategy(pricingType);
        CoffeeType coffeeType = CoffeeType.valueOf(type.toUpperCase());
        return facade.orderCoffee(customerName, coffeeType, strategy);
    }

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return facade.getOrderHistory();
    }

    @PostMapping("/clone/{orderId}")
    public Object cloneOrder(@PathVariable String orderId) {
        Object order = facade.getOrderHistory().stream()
                .filter(o -> matchesId(o, orderId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));

        prototypeOrder.setOrder((Order) order);
        return prototypeOrder.cloneOrder();
    }

    private PricingStrategy determineStrategy(String pricingType) {
        return switch (pricingType.toLowerCase()) {
            case "gold" -> new GoldPricing();
            case "silver" -> new SilverPricing();
            default -> new RegularPricing();
        };
    }

    private boolean matchesId(Object order, String orderId) {
        return ((Order) order).getId().equals(Long.parseLong(orderId));
    }
}
