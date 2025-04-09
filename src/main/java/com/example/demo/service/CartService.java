package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.model.Cart;
import com.example.demo.model.User;

public interface CartService {

	Cart addToCart(User user, Book book);
	Cart getCart(User user);
	void removeFromCart(User user, Long bookId);
}
