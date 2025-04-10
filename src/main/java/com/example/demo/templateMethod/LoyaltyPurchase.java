package com.example.demo.templateMethod;

import com.example.demo.model.Cart;
import com.example.demo.model.User;
import com.example.demo.model.UserOrder;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.UserOrderRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CartItemService;

public class LoyaltyPurchase extends Purchase {

	private final UserOrderRepository orderRepository;
    private final UserRepository userRepository;

    public LoyaltyPurchase(UserOrderRepository orderRepository, UserRepository userRepository, 
    		Cart cart, CartRepository cartRepository, CartItemService itemService) {
    	super(cart, cartRepository, itemService);
		this.orderRepository = orderRepository;
		this.userRepository = userRepository;
    }

    @Override
    protected double applyDiscount(User user, double total) {
        if (user.getLoyaltyPoints() >= 100) {
            System.out.println("Using 100 points for 10% discount.");
            user.setLoyaltyPoints(user.getLoyaltyPoints() - 100);
            return total * 0.90;
        } else {
            System.out.println("Not enough points for loyalty discount.");
            return total;
        }
    }

    @Override
    protected void saveOrder(UserOrder order) {
    	orderRepository.save(order);
    	userRepository.save(order.getUser());
        System.out.println("Loyalty purchase complete.");
    }

	@Override
	protected void processPayment() {
		 System.out.println("Processing payment.");
	}
}

