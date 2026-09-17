package com.coffeeshop.coffee_shop_spring.model;

public class Latte implements Coffee{
    @Override
    public String getDescription() {
        return "Latte";
    }

    @Override
    public Double getCost() {
        return 3.49;
    }
}
