package com.example.demo.service;

import java.util.List;

import com.example.demo.model.UserOrder;

public interface UserOrderService {
	List<UserOrder> getAllUserOrders();
	
	public void completeOrder(Long id, String promoCode, boolean usePoints);
}
