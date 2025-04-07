package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Book;
import com.example.demo.singleton.Cart;

@Service
public class CartServiceImpl implements CartService{
	
	@Override
	public Cart addToCart(Book book) {
		Cart cart = Cart.getInstance();
		cart.addBook(book);
		return cart;
	}

}
