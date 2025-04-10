package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;

public interface CartItemService {

	CartItem addCartItem(CartItem cartItem);
	
	List<CartItem> getByCart(Cart cart);
	
	void deleteCartItem(Long id);
}
