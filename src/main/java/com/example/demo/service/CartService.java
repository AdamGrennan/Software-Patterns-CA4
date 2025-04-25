package com.example.demo.service;

import java.util.Map;

import com.example.demo.model.Cart;
import com.example.demo.model.User;

public interface CartService {

	Cart addToCart(Long bookId, Long userId);
	Cart getCart(User user);
	void removeFromCart(User user, Long bookId);
	void clearCart(Cart cart);
	Map<String, Object> getDiscountTotal(User user, String promoCode, boolean usePoints);
}
