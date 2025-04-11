package com.example.demo.observer;

import com.example.demo.model.User;

public class LoyaltyPointsObserver implements PointObserver {
    @Override
    public void update(User user, double totalSpent) {
        int pointsEarned = (int) totalSpent;
        user.setLoyaltyPoints(user.getLoyaltyPoints() + pointsEarned);
    }
}
