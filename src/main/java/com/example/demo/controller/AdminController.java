package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.example.demo.proxy.AdminProxy;
import com.example.demo.proxy.IAdminAccess;


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
	
	@GetMapping("/book_form")
	public String bookForm(HttpSession session) {
		User user = (User) session.getAttribute("user");
	    
	    IAdminAccess access = new AdminProxy(user);
	    String result = access.performAction();
	    
	    if (result.equals("unauthorized")) {
	        return "unauthorized"; 
	    }
		
		return "book_form";
	}
	
	@PostMapping("/addBook")
	public String addBook(Book book, HttpSession session) {
		User user = (User) session.getAttribute("user");
	    
	    IAdminAccess access = new AdminProxy(user);
	    String result = access.performAction();
	    
	    if (result.equals("unauthorized")) {
	        return "unauthorized"; 
	    }
		bookService.addBook(book);
		return "redirect:/admin/administrator";
	}
	
	@PostMapping("/deleteBook") 
	public String deleteBook(@RequestParam Long id) {
	    bookService.deleteBook(id);
	    return "redirect:/admin/administrator";
	}

	
	@GetMapping("/updateStock")
	public String updateForm(@RequestParam Long bookId, HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
	    
	    IAdminAccess access = new AdminProxy(user);
	    String result = access.performAction();
	    
	    if (result.equals("unauthorized")) {
	        return "unauthorized"; 
	    }
	    
	    Book book = bookService.getBookById(bookId);
	    model.addAttribute("book", book);
	    return "update_book";
	}
	
	@PostMapping("/updateStock")
	public String updateBook(@RequestParam Long id, @ModelAttribute Book book) {
		bookService.updateBook(id, book);
		return "redirect:/admin/administrator";
	}

	@GetMapping("/viewCustomers")
	public String viewCustomers(HttpSession session, Model model) {
	    User user = (User) session.getAttribute("user");

	    IAdminAccess access = new AdminProxy(user);
	    String result = access.performAction();

	    if (result.equals("unauthorized")) {
	        return "unauthorized"; 
	    }

	    List<User> users = userService.getAllCustomers();
	    model.addAttribute("users", users);
	    return "customer_details";
	}

	@PostMapping("/simulateStock")
	public String simulateStock(@RequestParam Long bookId, @RequestParam int quantity, HttpSession session) {
	    User user = (User) session.getAttribute("user");

	    IAdminAccess access = new AdminProxy(user);
	    String result = access.performAction();

	    if (result.equals("unauthorized")) {
	        return "unauthorized"; 
	    }

	    adminService.simulateStock(bookId, quantity);
	    return "redirect:/admin/administrator";
	}

    
	@GetMapping("/administrator")
	public String administrator(Model model) {
		List<Book> books = bookService.getAllBooks(); 
        model.addAttribute("books", books);
		return "administrator";
	}

}
