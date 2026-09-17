package com.coffeeshop.coffee_shop_spring.decorator;

import com.coffeeshop.coffee_shop_spring.model.Coffee;

public class MilkDecorator extends CoffeeDecorator{
    MilkDecorator(Coffee coffee){
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Milk";
    }

    @Override
    public Double getCost() {
        return super.getCost() + 0.50;
    }
}
