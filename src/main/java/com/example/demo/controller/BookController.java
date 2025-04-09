package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@PostMapping("/addBook")
	public ResponseEntity<Book> addBook(Book book) {
		Book savedBook = bookService.addBook(book);
		return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
	}
	
	@GetMapping("/search")
	public String searchBooks(@RequestParam String searchBy, @RequestParam String query, Model model) {
	    List<Book> books = bookService.searchBooks(searchBy, query);
	    model.addAttribute("books", books);
	    return "home";
	}

	@GetMapping("/sort")
	public String sortBooks(@RequestParam String sortBy, @RequestParam String order, Model model) {
	    boolean ascending = order.equalsIgnoreCase("asc");
	    List<Book> books = bookService.sortBooks(sortBy, ascending);
	    model.addAttribute("books", books);
	    return "fragments/bookTable :: bookRows";
	}

	
	@GetMapping("/getAllBooks")
	public List<Book> getAllBooks() {
	    return bookService.getAllBooks();
	}

}
;