package com.coffeeshop.coffee_shop_spring.model;

public class Espresso implements Coffee{
    @Override
    public Double getCost() {
        return 1.99;
    }

    @Override
    public String getDescription() {
        return "Espresso";
    }
}
