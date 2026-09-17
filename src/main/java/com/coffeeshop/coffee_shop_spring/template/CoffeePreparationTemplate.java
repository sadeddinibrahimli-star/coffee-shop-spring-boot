package com.coffeeshop.coffee_shop_spring.template;

import com.coffeeshop.coffee_shop_spring.model.Coffee;

public abstract class CoffeePreparationTemplate {
    public final String prepareCoffee(Coffee coffee){
        grindBeans();
        brewCoffee();
        addExtras();
        serve();
        return "Your " + coffee.getDescription() + " is ready!";
    }
    abstract void grindBeans();
    abstract void brewCoffee();
    abstract void addExtras();
    final void serve(){
        System.out.println("Serving... 💅💅");
    }
}
