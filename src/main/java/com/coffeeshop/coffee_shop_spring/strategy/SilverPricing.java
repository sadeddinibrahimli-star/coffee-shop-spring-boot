package com.coffeeshop.coffee_shop_spring.strategy;

import org.springframework.stereotype.Component;

@Component
public class SilverPricing implements PricingStrategy {

    public Double calculatePrice(Double basePrice){
        return basePrice * 0.9;
    }
}
