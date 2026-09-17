package com.coffeeshop.coffee_shop_spring.controller;

import com.coffeeshop.coffee_shop_spring.model.CoffeeType;
import com.coffeeshop.coffee_shop_spring.model.Order;
import com.coffeeshop.coffee_shop_spring.repository.ChatRepository;
import com.coffeeshop.coffee_shop_spring.repository.OrderRepository;
import com.coffeeshop.coffee_shop_spring.singleton.CoffeeShop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ShopViewController {

    private final CoffeeShop coffeeShop;
    private final OrderRepository orderRepository;
    private final ChatRepository chatRepository;

    @Autowired
    public ShopViewController(CoffeeShop coffeeShop, OrderRepository orderRepository, ChatRepository chatRepository) {
        this.coffeeShop = coffeeShop;
        this.orderRepository = orderRepository;
        this.chatRepository = chatRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "index";
    }

    @PostMapping("/order")
    public String placeOrder(
            @RequestParam String customerName,
            @RequestParam String coffeeType,
            RedirectAttributes redirectAttributes) {
        try {
            CoffeeType type = CoffeeType.valueOf(coffeeType.toUpperCase());
            coffeeShop.placeOrder(type, customerName);
            redirectAttributes.addFlashAttribute("message", "Order placed successfully! Your " + coffeeType + " is being prepared.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error placing order: " + e.getMessage());
        }
        return "redirect:/";
    }

    @GetMapping("/orders")
    public String viewOrders(Model model) {
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/chat")
    public String chat(Model model) {
        model.addAttribute("messages", chatRepository.findAll());
        return "chat";
    }
}
