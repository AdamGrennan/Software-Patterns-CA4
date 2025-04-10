package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Book;
import com.example.demo.model.User;
import com.example.demo.service.BookService;
import com.example.demo.service.CartService;
import com.example.demo.service.UserOrderService;
import com.example.demo.service.UserService;
import com.example.demo.model.Cart;
import com.example.demo.model.OrderItem;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private UserOrderService orderService;
	

	@PostMapping("/addToCart")
	@ResponseBody
	public String addToCart(@RequestParam Long bookId, HttpSession session) {
	    User user = (User) session.getAttribute("user");
	    cartService.addToCart(bookId, user.getId());
	    return "Book added to cart!";
	}

	
	@GetMapping("/viewCart")
	public String viewCart(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		Cart cart = cartService.getCart(user);
		model.addAttribute("cart", cart);
	    model.addAttribute("books", cart.getList());
	    model.addAttribute("totalPrice", cart.getTotalPrice());
	   
	    if (user != null) {
	        model.addAttribute("user", user);
	        model.addAttribute("loyaltyPoints", user.getLoyaltyPoints());
	    }
	    return "cart"; 
	}
	
	@PostMapping("/completeOrder")
	public String completeOrder(
	        @RequestParam(required = false) String promoCode,
	        @RequestParam(defaultValue = "false") boolean usePoints,
	        HttpSession session) {
		
	    User user = (User) session.getAttribute("user");
	    orderService.completeOrder(user.getId(), promoCode, usePoints);
	    return "redirect:/cart/viewCart";
	}
	
	
	
	@PostMapping("/removeFromCart")
	public String removeFromCart(@RequestParam Long bookId, HttpSession session) {
	    User user = (User) session.getAttribute("user");
	    cartService.removeFromCart(user, bookId);
	    return "redirect:/cart/viewCart";
	}


}
