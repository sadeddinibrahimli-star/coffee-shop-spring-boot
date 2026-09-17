package com.coffeeshop.coffee_shop_spring.template;

import org.springframework.stereotype.Component;

@Component
public class CappuccinoPreparation extends CoffeePreparationTemplate {
    @Override
    void grindBeans() {
        System.out.println("Grinding medium-fine beans...");
    }
    @Override
    void brewCoffee() {
        System.out.println("Brewing double espresso shot...");
    }
    @Override
    void addExtras() {
        System.out.println("Frothing milk for thick cappuccino foam...");
    }
}
