package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String addUser(User user, Model model) {
		User savedUser = userService.addUser(user);
		if (savedUser != null) {
			   return "redirect:/";
		} else {
			model.addAttribute("error", "User already exists.");
	        return "register"; 
		}
	}
	
	@PostMapping("/login")
	public String login(User user, HttpSession session, Model model) {
	    User found = userService.login(user.getEmail(), user.getPassword());

	    if (found != null) {
	        session.setAttribute("user", found);

	        if (found.getRole() == Role.ADMINISTRATOR) {
	            return "redirect:/administrator";
	        } else {
	            return "redirect:/home";
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

    @GetMapping("/register")
	public String register() {
		return "register";
	}
    
    @GetMapping("/")
   	public String login() {
   		return "login";
   	}

    



}
