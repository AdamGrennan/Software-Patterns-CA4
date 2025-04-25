package com.example.demo.discount;

import com.example.demo.model.User;

public class LoyaltyDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(User user, double total) {
        if (user.getLoyaltyPoints() >= 100) {
            user.setLoyaltyPoints(user.getLoyaltyPoints() - 100);
            return total * 0.90;
        }
        return total;
    }
}
