package com.coffeeshop.coffee_shop_spring.template;

import org.springframework.stereotype.Component;

@Component
public class EspressoPreparation extends CoffeePreparationTemplate{

    @Override
    void grindBeans() {
        System.out.println("Grinding fine espresso beans...");
    }

    @Override
    void brewCoffee() {
        System.out.println("Brewing with high pressure...");
    }

    @Override
    void addExtras() {
        System.out.println("No extras for espresso");
    }
}
