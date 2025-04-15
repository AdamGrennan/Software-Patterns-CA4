package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Book;
import com.example.demo.model.User;
import com.example.demo.service.AdminService;
import com.example.demo.service.BookService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model; 


@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/updateStock")
	public String updateForm(@RequestParam Long bookId, Model model) {
	    Book book = bookService.getBookById(bookId);
	    model.addAttribute("book", book);
	    return "update_book";
	}


	@PostMapping("/updateStock")
	@ResponseBody
	public String updateBook(@RequestParam Long id, @ModelAttribute Book book) {
		adminService.updateBook(id, book);
		return "administrator";
	}
	

    @GetMapping("/viewCustomers")
    public String viewCustomers(Model model) {
        List<User> users = userService.getAllUsers(); 
        model.addAttribute("users", users);
        return "customer_details";
    }
}
