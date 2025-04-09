package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Book;
import com.example.demo.model.Cart;
import com.example.demo.model.OrderItem;
import com.example.demo.model.User;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.OrderItemRepository;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Override
	public Cart addToCart(User user, Book book) {
	    Cart cart = cartRepository.findByUser(user);
	    if (cart == null) {
	        cart = new Cart();
	        cart.setUser(user);
	    }

	    OrderItem item = new OrderItem();
	    item.setBook(book);
	    item.setQuantity(1);
	    item.setPrice(book.getPrice());
	    item.setCart(cart);

	    cart.getList().add(item);
	    return cartRepository.save(cart);
	}

	public Cart getCart(User user) {
	    Cart cart = cartRepository.findByUser(user);
	    if (cart == null) {
	        cart = new Cart();
	        cart.setUser(user);
	        double totalPrice = 0;
		    for (OrderItem item : cart.getList()) {
		        totalPrice += item.getPrice();
		    }

		    cart.setTotalPrice(totalPrice); 
	        cartRepository.save(cart);
	    }
	    return cart;
	}
	


	@Override
	public void removeFromCart(User user, Long bookId) {
	    Cart cart = cartRepository.findByUser(user);

	    OrderItem itemToRemove = null;

	    for (OrderItem item : cart.getList()) {
	        if (item.getBook().getId().equals(bookId)) {
	            itemToRemove = item;
	            break;
	        }
	    }
	    if (itemToRemove != null) {
	        cart.getList().remove(itemToRemove);       
	        orderItemRepository.delete(itemToRemove);   
	        cartRepository.save(cart);                  
	    }
	}


}
