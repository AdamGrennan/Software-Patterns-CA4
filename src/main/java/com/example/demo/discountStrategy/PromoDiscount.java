package com.example.demo.discountStrategy;

import com.example.demo.model.User;

public class PromoDiscount implements DiscountStrategy {
    private final String promoCode;

    public PromoDiscount(String promoCode) {
        this.promoCode = promoCode;
    }

    @Override
    public double applyDiscount(User user, double total) {
        if ("SAVE20".equalsIgnoreCase(promoCode)) {
            return total * 0.80; 
        }
        return total;
    }
    
    public boolean isValid() {
        return "SAVE20".equalsIgnoreCase(promoCode);
    }
}
