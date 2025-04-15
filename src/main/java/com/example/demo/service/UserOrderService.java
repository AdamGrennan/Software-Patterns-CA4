package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;
import com.example.demo.model.UserOrder;

import jakarta.servlet.http.HttpSession;

public interface UserOrderService {
	List<UserOrder> getAllUserOrders();
	String completeOrder(Long id, String promoCode, boolean usePoints, HttpSession session);
	List<UserOrder> getUserOrders(User user);
}
