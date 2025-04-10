package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.User;
import com.example.demo.model.UserOrder;
import com.example.demo.service.UserOrderService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	private UserOrderService orderService;
	
	@GetMapping("/viewOrders")
	public String viewOrders(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		List<UserOrder> orders = orderService.getUserOrders(user);
		model.addAttribute("orders", orders);
	   
	    if (user != null) {
	        model.addAttribute("user", user);
	        model.addAttribute("loyaltyPoints", user.getLoyaltyPoints());
	    }
	    return "user_orders"; 
	}

}
