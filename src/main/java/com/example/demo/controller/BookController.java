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
import com.example.demo.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@PostMapping("/addBook")
	public String addBook(Book book) {
		bookService.addBook(book);
		return "administrator";
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
	
	@PostMapping("/updateStock")
	public String updateBook(@RequestParam Long id, @ModelAttribute Book book) {
		bookService.updateBook(id, book);
		return "redirect:/administrator";
	}
	
	@GetMapping("/getAllBooks")
	public List<Book> getAllBooks() {
	    return bookService.getAllBooks();
	}
	
	@GetMapping("/book_form")
	public String bookForm() {
		return "book_form";
	}
	

}
;