package com.example.demo.discount;

import com.example.demo.model.User;

public interface DiscountStrategy {
    double applyDiscount(User user, double total);
}
