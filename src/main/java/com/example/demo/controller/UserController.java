package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/addUser")
	public String addUser(User user, Model model) {
		User savedUser = userService.addUser(user);
		if (savedUser != null) {
			   return "redirect:/login";
		} else {
			model.addAttribute("error", "User already exists.");
	        return "register"; 
		}
	}
	
	@PostMapping("/login")
	public String login(User user, HttpSession session, Model model) {
		User loginUser = userService.login(user.getEmail(), user.getPassword());
		if (loginUser != null) {
			 session.setAttribute("user", loginUser);
			 return "redirect:/home";
		} else {
			model.addAttribute("error", "User  not found.");
	        return "login"; 
		}
		
	}

}
