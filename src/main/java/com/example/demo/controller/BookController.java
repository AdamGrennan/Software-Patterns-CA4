package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Book;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.BookService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/search")
	public String searchBooks(
	    @RequestParam String searchBy, @RequestParam String query, Model model, HttpSession session) {
	    List<Book> books = bookService.searchBooks(searchBy, query);
	    model.addAttribute("books", books);

	    User user = (User) session.getAttribute("user");
	    if (user != null && user.getRole() == Role.ADMINISTRATOR) {
	        return "administrator";
	    } else {
	        return "home";
	    }
	}

	@GetMapping("/sort")
	public String sortBooks(@RequestParam String sortBy, @RequestParam String order, 
		@RequestParam(required = false) String searchBy, @RequestParam(required = false) String query,
	    Model model, HttpSession session) {
	    boolean ascending = order.equalsIgnoreCase("asc");

	    List<Book> books;
	    if (searchBy != null && query != null && !searchBy.isEmpty() && !query.isEmpty()) {
	        books = bookService.searchBooks(searchBy, query);
	        books = bookService.sortBooks(books, sortBy, ascending); 
	    } else {
	        books = bookService.sortBooks(sortBy, ascending);
	    }

	    model.addAttribute("books", books);
	    User user = (User) session.getAttribute("user");
	    if (user != null && user.getRole() == Role.ADMINISTRATOR) {
	        return "adminTable :: bookRows";
	    } else {
	        return "bookTable :: bookRows";
	    }
	}
	
	@GetMapping("/getAllBooks")
	public List<Book> getAllBooks() {
	    return bookService.getAllBooks();
	}
	

}
;