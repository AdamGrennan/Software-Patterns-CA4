package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Book;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.BookService;
import com.example.demo.service.UserService;


import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BookService bookService;

	@PostMapping("/addUser")
	public String addUser(User user, Model model, @RequestParam(required = false) String adminKey) {
		try {
			if (user.getRole() == Role.ADMINISTRATOR) {
		        String ADMIN_KEY = "book123"; 
		         if (adminKey == null || !adminKey.equals(ADMIN_KEY)) {
		                model.addAttribute("error", "Invalid Admin Key.");
		                return "register";
		            }
		        }
		User savedUser = userService.addUser(user);
		if (savedUser != null) {
			   return "redirect:/";
		} else {
			model.addAttribute("error", "User already exists.");
	        return "register"; 
		}
		}catch (IllegalArgumentException e) {
	        model.addAttribute("error", e.getMessage());
	        return "register";
	    }
	}
	
	@PostMapping("/login")
	public String login(User user, HttpSession session, Model model) {
	    User found = userService.login(user.getEmail(), user.getPassword());

	    if (found != null) {
	        session.setAttribute("user", found);

	        if (found.getRole() == Role.ADMINISTRATOR) {
	            return "redirect:/admin/administrator";
	        } else {
	            return "redirect:/users/home";
	        }
	    }

	    model.addAttribute("error", "Invalid credentials");
	    return "login";
	}
	
    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        List<Book> books = bookService.getAllBooks(); 
        model.addAttribute("books", books);
        model.addAttribute("user", user);
        model.addAttribute("loyaltyPoints", user.getLoyaltyPoints());
        return "home";
    }
   
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate(); 
	    return "redirect:/";
	}
	
	@GetMapping("/unauthorized")
	public String showUnauthorizedPage() {
	    return "unauthorized";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
	    model.addAttribute("user", new User()); 
	    return "register";
	}

}
