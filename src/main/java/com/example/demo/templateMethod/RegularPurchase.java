package com.example.demo.templateMethod;

import com.example.demo.model.Cart;
import com.example.demo.model.UserOrder;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.UserOrderRepository;
import com.example.demo.repository.UserRepository;

public class RegularPurchase extends Purchase {

	private final UserOrderRepository orderRepository;
    private final UserRepository userRepository;

    public RegularPurchase(UserOrderRepository orderRepository, UserRepository userRepository, 
    		Cart cart, CartRepository cartRepository) {
        super(cart, cartRepository);
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }



    @Override
    protected void saveOrder(UserOrder order) {
    	orderRepository.save(order);
    	userRepository.save(order.getUser());
        System.out.println("Regular purchase complete.");
    }


	@Override
	protected void processPayment() {
		 System.out.println("Processing payment.");
	}

}
