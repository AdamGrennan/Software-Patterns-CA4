package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Book;
import com.example.demo.model.User;
import com.example.demo.service.BookService;
import com.example.demo.service.UserService;

@Controller
public class WebController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/home")
	public String home() {
		return "Home";
	}

	@GetMapping("/register")
	public String register() {
		return "Register";
	}

	@PostMapping("/register")
	public String addUser(@ModelAttribute User user) {
		userService.addUser(user);
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String loginPage() {
		return "Login";
	}

	@PostMapping("/login")
	public String loginUser(@ModelAttribute User user) {
		User userCheck = userService.loginUser(user.getUsername(), user.getPassword());
		if(userCheck != null) {
			return "redirect:/getEmissions";
		}else {
			return "Login";
		}
	
	}

	@GetMapping("/search")
	public String search(@RequestParam String search, Model model) {
		List<Book> emissions = bookService.getBooksByCategory(search);
		model.addAttribute("emissions", emissions);
		return "Home";
	}

}
