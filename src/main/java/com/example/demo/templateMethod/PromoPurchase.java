package com.example.demo.templateMethod;

import com.example.demo.model.Cart;
import com.example.demo.model.User;
import com.example.demo.model.UserOrder;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.UserOrderRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CartItemService;

public class PromoPurchase extends Purchase{
	
    private final UserOrderRepository orderRepository;
    private final UserRepository userRepository;
    private final String promoCode;

    public PromoPurchase(UserOrderRepository orderRepository, UserRepository userRepository, 
    		String promoCode, Cart cart, CartRepository cartRepository, CartItemService itemService) {
    	super(cart, cartRepository, itemService);
		this.orderRepository = orderRepository;
		this.userRepository = userRepository;
        this.promoCode = promoCode;
    }

    @Override
    protected double applyDiscount(User user, double total) {
    	if (promoCode.equalsIgnoreCase("BOOK25")) {
    	    return total * 0.75;
    	} else if (promoCode.equalsIgnoreCase("WELCOME10")) {
    	    return total * 0.90;
    	} else {
    	    return total;
    	}

    }

	@Override
	protected void saveOrder(UserOrder order) {
		orderRepository.save(order);
		userRepository.save(order.getUser());
	    System.out.println("Promo purchase complete.");
	}

	@Override
	protected void processPayment() {
		System.out.println("Processing payment.");
	}


}
