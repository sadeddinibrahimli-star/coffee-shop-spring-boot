package com.coffeeshop.coffee_shop_spring.factory;

import com.coffeeshop.coffee_shop_spring.model.*;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Supplier;

import static com.coffeeshop.coffee_shop_spring.model.CoffeeType.*;

@Service
public class CoffeeFactory {

    private final Map<CoffeeType, Supplier<Coffee>> constructor;
    public CoffeeFactory() {
        this.constructor = Map.of(
                ESPRESSO, Espresso::new,
                CAPPUCCINO, Cappuccino::new,
                LATTE, Latte::new
        );
    }

    public Coffee createCoffee(CoffeeType type) {
        Supplier<Coffee> supplier = this.constructor.get(type);
        if (supplier == null) {
            throw new IllegalArgumentException("Unknown coffee type: " + type);
        }
        return supplier.get();
    }
}