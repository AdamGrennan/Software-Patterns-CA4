package com.example.demo.controller;

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
import com.example.demo.service.CartService;
import com.example.demo.singleton.Cart;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private BookService bookService;

	@PostMapping("/addToCart")
	public ResponseEntity<Cart> addToCart(@RequestParam Long bookId) {
	    Book book = bookService.getBookById(bookId);
	    Cart saveToCart = cartService.addToCart(book);
	    return new ResponseEntity<>(saveToCart, HttpStatus.CREATED);
	}

	
	@GetMapping("/viewCart")
	public String viewCart(Model model) {
	    Cart cart = Cart.getInstance(); 
	    model.addAttribute("cart", cart);
	    return "cart"; 
	}

	
}
