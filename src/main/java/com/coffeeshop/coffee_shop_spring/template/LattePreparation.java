package com.coffeeshop.coffee_shop_spring.template;

import org.springframework.stereotype.Component;

@Component
public class LattePreparation extends CoffeePreparationTemplate{
    @Override
    void grindBeans() {
        System.out.println("Grinding medium beans for latte...");
    }

    @Override
    void brewCoffee() {
        System.out.println("Brewing espresso shot...");
    }

    @Override
    void addExtras() {
        System.out.println("Steaming milk for latte...");
    }
}
