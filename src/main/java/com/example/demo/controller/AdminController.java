package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.facade.AdminFacade;
import com.example.demo.model.Book;
import com.example.demo.model.User;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model; 


@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminFacade adminFacade;

	
	@GetMapping("/book_form")
	public String bookForm(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (!adminFacade.isAuthorized(user)) {
		    return "unauthorized"; 
		}
		return "book_form";
	}
	
	@PostMapping("/addBook")
	public String addBook(Book book, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (!adminFacade.isAuthorized(user)) {
		    return "unauthorized"; 
		}
		adminFacade.addBook(book);
		return "redirect:/admin/administrator";
	}
	
	/*
	@PostMapping("/deleteBook") 
	public String deleteBook(@RequestParam Long id) {
		adminFacade.deleteBook(id);
	    return "redirect:/admin/administrator";
	}
	*/
	
	@GetMapping("/updateStock")
	public String updateForm(@RequestParam Long bookId, HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (!adminFacade.isAuthorized(user)) {
		    return "unauthorized"; 
		}
	    Book book = adminFacade.getBookById(bookId);
	    model.addAttribute("book", book);
	    return "update_book";
	}
	
	@PostMapping("/updateStock")
	public String updateBook(@RequestParam Long id, @ModelAttribute Book book) {
		adminFacade.updateBook(id, book);
		return "redirect:/admin/administrator";
	}

	@GetMapping("/viewCustomers")
	public String viewCustomers(HttpSession session, Model model) {
	    User user = (User) session.getAttribute("user");
	    if (!adminFacade.isAuthorized(user)) {
		    return "unauthorized"; 
		}

	    List<User> users = adminFacade.getAllCustomers();
	    model.addAttribute("users", users);
	    return "customer_details";
	}

	@PostMapping("/simulateStock")
	public String simulateStock(@RequestParam Long bookId, @RequestParam int quantity, HttpSession session) {
	    User user = (User) session.getAttribute("user");
	    if (!adminFacade.isAuthorized(user)) {
		    return "unauthorized"; 
		}

	    adminFacade.simulateStock(bookId, quantity);
	    return "redirect:/admin/administrator";
	}

    
	@GetMapping("/administrator")
	public String administrator(Model model) {
		List<Book> books = adminFacade.getAllBooks(); 
        model.addAttribute("books", books);
		return "administrator";
	}

}
