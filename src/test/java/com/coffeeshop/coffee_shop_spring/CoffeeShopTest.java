package com.coffeeshop.coffee_shop_spring;

import com.coffeeshop.coffee_shop_spring.model.Coffee;
import com.coffeeshop.coffee_shop_spring.model.CoffeeType;
import com.coffeeshop.coffee_shop_spring.model.Order;
import com.coffeeshop.coffee_shop_spring.singleton.CoffeeShop;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CoffeeShopTest {

    @Autowired
    private CoffeeShop coffeeShop;

    @Test
    void testPlaceOrder() throws InterruptedException {
        Coffee coffee = coffeeShop.placeOrder(CoffeeType.ESPRESSO, "TestUser");
        assertNotNull(coffee);
        assertEquals("Espresso", coffee.getDescription());
        assertEquals(1.99, coffee.getCost());
        assertFalse(coffeeShop.getOrders().isEmpty());
    }

    @Test
    void testGetOrders() throws InterruptedException {
        int beforeSize = coffeeShop.getOrders().size();
        coffeeShop.placeOrder(CoffeeType.CAPPUCCINO, "User1");
        coffeeShop.placeOrder(CoffeeType.LATTE, "User2");
        assertEquals(beforeSize + 2, coffeeShop.getOrders().size());
    }
}
