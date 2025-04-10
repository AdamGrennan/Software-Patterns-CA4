package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Book;
import com.example.demo.model.User;
import com.example.demo.model.UserOrder;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.UserOrderRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.model.OrderItem;
import com.example.demo.templateMethod.LoyaltyPurchase;
import com.example.demo.templateMethod.PromoPurchase;
import com.example.demo.templateMethod.Purchase;
import com.example.demo.templateMethod.RegularPurchase;

@Service
public class UserOrderServiceImpl implements UserOrderService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserOrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartItemService itemService; 

	@Override
	public List<UserOrder> getAllUserOrders() {
		return null;
		
	}
	
	public void completeOrder(Long id, String promoCode, boolean usePoints) {
	    User user = userRepository.findById(id).orElse(null);
	    Cart cart = cartRepository.findByUser(user);

	    UserOrder order = new UserOrder();
	    order.setUser(user);
	    order = orderRepository.save(order);

	    Purchase purchase;
	    if (promoCode != null && !promoCode.isEmpty()) {
	        purchase = new PromoPurchase(orderRepository, userRepository, promoCode, cart, cartRepository, itemService);
	    } else if (usePoints && user.getLoyaltyPoints() >= 100) {
	        purchase = new LoyaltyPurchase(orderRepository, userRepository, cart, cartRepository, itemService);
	    } else {
	        purchase = new RegularPurchase(orderRepository, userRepository, cart, cartRepository, itemService);
	    }

	    List<OrderItem> orderItems = new ArrayList<>();
	    for (CartItem item : cart.getList()) {
	        Book book = item.getBook();
	        int quantity = item.getQuantity();

	        book.setStock(book.getStock() - quantity);
	        bookRepository.save(book);

	        OrderItem orderItem = new OrderItem();
	        orderItem.setBook(book);
	        orderItem.setQuantity(quantity);
	        orderItem.setPrice(item.getPrice());
	        orderItem.setOrder(order);

	        orderItem = orderItemRepository.save(orderItem);
	        orderItems.add(orderItem);
	    }

	    order.setList(orderItems);
	    orderRepository.save(order); 

	    purchase.completeOrder(order);
	}
	
	public List<UserOrder> getUserOrders(User user){
		return orderRepository.findByUser(user);

	}

}
