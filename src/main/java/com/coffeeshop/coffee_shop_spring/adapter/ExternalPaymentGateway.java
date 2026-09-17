package com.coffeeshop.coffee_shop_spring.adapter;

public class ExternalPaymentGateway{
    public boolean pay(Double amount){
        System.out.println("External gateway processing: $" + amount);
        return true;
    }
}
