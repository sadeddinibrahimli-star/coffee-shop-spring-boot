package com.coffeeshop.coffee_shop_spring.facade;

import com.coffeeshop.coffee_shop_spring.adapter.PaymentAdapter;
import com.coffeeshop.coffee_shop_spring.model.Coffee;
import com.coffeeshop.coffee_shop_spring.model.CoffeeType;
import com.coffeeshop.coffee_shop_spring.model.Order;
import com.coffeeshop.coffee_shop_spring.singleton.CoffeeShop;
import com.coffeeshop.coffee_shop_spring.strategy.PricingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeShopFacade {
    final CoffeeShop coffeeShop;
    final PaymentAdapter paymentAdapter;
    @Autowired
    CoffeeShopFacade(CoffeeShop coffeeShop , PaymentAdapter paymentAdapter){
        this.coffeeShop = coffeeShop;
        this.paymentAdapter = paymentAdapter;
    }
    public String orderCoffee(String custumerName , CoffeeType type , PricingStrategy pricingStrategy) throws InterruptedException {
        Coffee coffee = coffeeShop.placeOrder(type , custumerName);
        Double finalPrice = pricingStrategy.calculatePrice(coffee.getCost());
        boolean paid = paymentAdapter.processPayment(finalPrice);
        if (paid) return "Order complete: " + coffee.getDescription() + " - $" + finalPrice;
        else return "Payment failed";
    }
    public List<Order> getOrderHistory(){
        return coffeeShop.getOrders();
    }
}
