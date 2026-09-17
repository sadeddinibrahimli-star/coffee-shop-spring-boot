package com.coffeeshop.coffee_shop_spring.command;

import com.coffeeshop.coffee_shop_spring.model.CoffeeType;
import com.coffeeshop.coffee_shop_spring.singleton.CoffeeShop;

public class PlaceOrderCommand implements OrderCommand {
    final CoffeeShop coffeeShop;
    final CoffeeType coffeeType;
    final String custumerName;
    public PlaceOrderCommand ( CoffeeShop coffeeShop , CoffeeType coffeeType ,String custumerName) {
        this.coffeeShop = coffeeShop;
        this.coffeeType = coffeeType;
        this.custumerName = custumerName;
    }
    @Override
    public void execute() {
        try {
            coffeeShop.placeOrder(coffeeType, custumerName);
            System.out.println("Order Placed");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Order was interrupted");
        }
    }
}
