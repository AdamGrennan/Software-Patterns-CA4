package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BookService bookService;
	
	@Override
	public Cart addToCart(Long bookId, Long userId) {
	    Book book = bookService.getBookById(bookId);
	    User user = userService.getUserById(userId);

	    Cart cart = cartRepository.findByUser(user);
	    if (cart == null) {
	        cart = new Cart();
	        cart.setUser(user);
	        cart = cartRepository.save(cart); // Save so cart has an ID
	    }

	    OrderItem item = new OrderItem();
	    item.setBook(book);
	    item.setQuantity(1);
	    item.setPrice(book.getPrice());
	    item.setCart(cart);

	    orderItemRepository.save(item);

	    return cartRepository.findByUser(user); // Reload cart with updated list
	}



	@Override
	public Cart getCart(User user) {
	    Cart cart = cartRepository.findByUser(user);
	    if (cart == null) {
	        cart = new Cart();
	        cart.setUser(user);
	        cart.setTotalPrice(0);
	        return cart;
	    }

	    // 🛠 Force manual loading of items
	    cart.setList(orderItemRepository.findByCart(cart));

	    // 🧮 Recalculate total
	    double totalPrice = 0;
	    for (OrderItem item : cart.getList()) {
	        totalPrice += item.getPrice();
	    }

	    cart.setTotalPrice(totalPrice);
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
