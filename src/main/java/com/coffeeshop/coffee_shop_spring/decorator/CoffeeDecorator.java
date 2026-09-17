package com.coffeeshop.coffee_shop_spring.decorator;

import com.coffeeshop.coffee_shop_spring.model.Coffee;

public abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;
    CoffeeDecorator(Coffee coffee){
        this.coffee = coffee;
    }
    public String getDescription(){
        return coffee.getDescription();
    }
    public Double getCost(){
        return coffee.getCost();
    }

}
