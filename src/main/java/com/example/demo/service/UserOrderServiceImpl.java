package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Book;
import com.example.demo.model.User;
import com.example.demo.model.UserOrder;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.UserOrderRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.model.Cart;
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
	private BookRepository bookRepository;
	
	@Autowired
	private CartRepository cartRepository;

	@Override
	public List<UserOrder> getAllUserOrders() {
		return null;
		
	}
	
	public void completeOrder(Long id, String promoCode, boolean usePoints) {
		User user = userRepository.findById(id).orElse(null);
		UserOrder order = new UserOrder();
		Cart cart = cartRepository.findByUser(user);
		order.setUser(user);
		
		Purchase purchase;

        if (promoCode != null && !promoCode.isEmpty()) {
            purchase = new PromoPurchase(orderRepository, userRepository, promoCode, cart, cartRepository);
        } else if (usePoints && user.getLoyaltyPoints() >= 100) {
            purchase = new LoyaltyPurchase(orderRepository, userRepository, cart, cartRepository);
        } else {
            purchase = new RegularPurchase(orderRepository, userRepository, cart, cartRepository);
        }
        
        for (OrderItem item : cart.getList()) {
            Book book = item.getBook();
            int quantity = item.getQuantity();
            book.setStock(book.getStock() - quantity);
            bookRepository.save(book);
        }


        purchase.completeOrder(order);
        cart.getList().clear();
        cart.setTotalPrice(0);
        cartRepository.save(cart);
	}
}
