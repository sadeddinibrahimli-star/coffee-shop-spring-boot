package com.coffeeshop.coffee_shop_spring.decorator;

import com.coffeeshop.coffee_shop_spring.model.Coffee;

public class SugarDecorator extends CoffeeDecorator{
    SugarDecorator(Coffee coffee){
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Sugar";
    }

    @Override
    public Double getCost() {
        return super.getCost() + 0.25;
    }
}
