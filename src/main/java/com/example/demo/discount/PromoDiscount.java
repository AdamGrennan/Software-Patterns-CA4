package com.example.demo.discount;

import com.example.demo.model.User;

public class PromoDiscount implements DiscountStrategy {
    private final String promoCode;

    public PromoDiscount(String promoCode) {
        this.promoCode = promoCode;
    }

    @Override
    public double applyDiscount(User user, double total) {
        if ("BOOK20".equalsIgnoreCase(promoCode)) {
            return total * 0.80; 
        }
        return total;
    }
    
    public boolean isValid() {
        return "BOOK20".equalsIgnoreCase(promoCode);
    }
}
