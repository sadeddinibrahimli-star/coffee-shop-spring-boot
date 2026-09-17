package com.coffeeshop.coffee_shop_spring.adapter;

import org.springframework.stereotype.Component;

@Component
public class PaymentAdapter implements PaymentService{
    final ExternalPaymentGateway gateway = new ExternalPaymentGateway();
    @Override
    public boolean processPayment(Double amount) {
        return gateway.pay(amount);
    }
}
