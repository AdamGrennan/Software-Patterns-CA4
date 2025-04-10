package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.repository.CartItemRepository;

@Service
public class CartItemServiceImpl implements CartItemService{
	
	@Autowired
	private CartItemRepository cartItemRepository;
	

	@Override
	public CartItem addCartItem(CartItem cartItem) {
		return cartItemRepository.save(cartItem);
	}

	@Override
	public List<CartItem> getByCart(Cart cart) {
		return cartItemRepository.findByCart(cart);
		}
	
	@Override
	public void deleteCartItem(Long id) {
		cartItemRepository.deleteById(id);
  }
}
