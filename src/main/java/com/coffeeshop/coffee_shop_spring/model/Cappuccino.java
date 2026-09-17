package com.coffeeshop.coffee_shop_spring.model;

public class Cappuccino implements Coffee{
    @Override
    public Double getCost() {
        return 2.99;
    }

    @Override
    public String getDescription() {
        return "Cappuccino";
    }
}
