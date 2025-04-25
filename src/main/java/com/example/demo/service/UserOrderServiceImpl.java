package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.discount.LoyaltyDiscount;
import com.example.demo.discount.PromoDiscount;
import com.example.demo.model.Book;
import com.example.demo.model.User;
import com.example.demo.model.UserOrder;
import com.example.demo.repository.UserOrderRepository;
import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.model.OrderItem;

import jakarta.servlet.http.HttpSession;

@Service
public class UserOrderServiceImpl implements UserOrderService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserOrderRepository orderRepository;
	
	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private CartService cartService; 

	public String completeOrder(String promoCode, boolean usePoints, HttpSession session) {
	    User user = (User) session.getAttribute("user");
	    Cart cart = cartService.getCart(user);

	    if (cart == null || cart.getList().isEmpty()) {
	        return "redirect:/cart/viewCart?error=empty";
	    }

	    double originalTotal = cart.getTotalPrice();
	    double discountedTotal = originalTotal;

	    if (usePoints && user.getLoyaltyPoints() >= 100) {
	        discountedTotal = new LoyaltyDiscount().applyDiscount(user, discountedTotal);
	    } else if (usePoints) {
	        return "redirect:/cart/viewCart?error=not_enough_points";
	    }

	    if (promoCode != null && !promoCode.isEmpty()) {
	        discountedTotal = new PromoDiscount(promoCode).applyDiscount(user, discountedTotal);
	    }

	    double discountFactor;
	    if (originalTotal == 0) {
	        discountFactor = 1;
	    } else {
	        discountFactor = discountedTotal / originalTotal;
	    }

	    UserOrder order = new UserOrder();
	    order.setUser(user);
	    order = orderRepository.save(order);

	    List<OrderItem> orderItems = new ArrayList<>();
	    for (CartItem item : cart.getList()) {
	        Book book = item.getBook();
	        int quantity = item.getQuantity();

	        book.setStock(book.getStock() - quantity);
	        bookService.updateBook(book.getId(), book);

	        OrderItem orderItem = new OrderItem();
	        orderItem.setBook(book);
	        orderItem.setQuantity(quantity);

	        double originalItemPrice = item.getPrice();
	        double discountedPrice = originalItemPrice * discountFactor;

	        orderItem.setPrice(discountedPrice);
	        orderItem.setOrder(order);

	        orderItem = orderItemService.addItem(orderItem);
	        orderItems.add(orderItem);
	    }
	    
	    order.setList(orderItems);
	    order.setTotal(discountedTotal);
	    orderRepository.save(order);

	    cartService.clearCart(cart);

	    int earnedPoints = (int) discountedTotal;
	    user.setLoyaltyPoints(user.getLoyaltyPoints() + earnedPoints);

	    userService.updateUser(user.getId(), user); 
	    session.setAttribute("user", user);
	    session.removeAttribute("promoCode");

	    return "redirect:/cart/viewCart?success=complete";
	}

	
	public List<UserOrder> getUserOrders(User user){
		return orderRepository.findByUser(user);

	}


	@Override
	public List<UserOrder> getAllUserOrders() {
		// TODO Auto-generated method stub
		return null;
	}

}
;