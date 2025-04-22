package com.example.demo.discountStrategy;

import com.example.demo.model.User;

public class NoDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(User user, double total) {
        return total;
    }
}
