package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import com.example.demo.strategy.BookSorter;
import com.example.demo.strategy.SortByAuthor;
import com.example.demo.strategy.SortByCategory;
import com.example.demo.strategy.SortByPrice;
import com.example.demo.strategy.SortByPublisher;
import com.example.demo.strategy.SortByTitle;

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
	    List<Book> books;
	    
	    switch (searchBy) {
	        case "title":
	            books = bookService.getBooksByTitle(query);
	            break;
	        case "author":
	            books = bookService.getBooksByAuthor(query);
	            break;
	        case "publisher":
	            books = bookService.getBooksByPublisher(query);
	            break;
	        case "category":
	            books = bookService.getBooksByCategory(query);
	            break;
	        default:
	        	books = new ArrayList<>();
	    }

	    model.addAttribute("books", books);
	    return "home";
	}
	
	@GetMapping("/sort")
	public String sortBooks(@RequestParam String sortBy, @RequestParam String order, Model model) {
	    List<Book> books = bookService.getAllBooks();
	    BookSorter sorter = new BookSorter();
	    boolean ascending = order.equalsIgnoreCase("asc");
	    
	    switch (sortBy) {
	        case "title":
	        	 sorter.setStrategy(new SortByTitle());
	            break;
	        case "author":
	        	sorter.setStrategy(new SortByAuthor());
	            break;
	        case "publisher":
	        	sorter.setStrategy(new SortByPublisher());
	            break;
	        case "category":
	        	sorter.setStrategy(new SortByCategory());
	            break;
	        case "price":
	        	sorter.setStrategy(new SortByPrice());
	            break;
	        default:
	        	books = new ArrayList<>();
	    }

	    sorter.sortBooks(books, ascending);
	    model.addAttribute("books", books);
	    return "home";
	}
	
	@GetMapping("/getAllBooks")
	public List<Book> getAllBooks() {
	    return bookService.getAllBooks();
	}

}
;