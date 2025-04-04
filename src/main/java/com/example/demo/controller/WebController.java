package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.example.demo.model.Book;
import com.example.demo.model.User;
import com.example.demo.model.UserOrder;
import com.example.demo.service.BookService;
import com.example.demo.service.UserOrderService;
import com.example.demo.service.UserService;

@Controller
public class WebController {

	@Autowired
	private UserService userService;
	
	
	@Autowired
	private BookService bookService;
	

    @GetMapping("/")
    public String home() {
    	 return "home";
    }
    
    /**
    @GetMapping("/administrator")
	public String administrator(Model model) {
    	List<UserOrder> purchases = userOrderService.getAllUserOrders();
    	model.addAttribute("purchases", purchases);
		return "administrator";
	}
	**/
    
    @GetMapping("/register")
	public String register() {
		return "register";
	}
    
	@GetMapping("/administrator")
	public String administrator() {
		return "administrator";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/book_form")
	public String book_form() {
		return "book_form";
	}


}
