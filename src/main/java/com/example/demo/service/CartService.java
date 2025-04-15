package com.example.demo.service;

import com.example.demo.model.Cart;
import com.example.demo.model.User;

public interface CartService {

	Cart addToCart(Long bookId, Long userId);
	Cart getCart(User user);
	void removeFromCart(User user, Long bookId);
	void clearCart(Cart cart);
}
