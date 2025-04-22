package com.example.demo.discountStrategy;

import com.example.demo.model.User;

public interface DiscountStrategy {
    double applyDiscount(User user, double total);
}
