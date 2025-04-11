package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Book;
import com.example.demo.model.User;
import com.example.demo.service.BookService;

import jakarta.servlet.http.HttpSession;

@Controller
public class WebController {
	
	@Autowired
	private BookService bookService;

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        List<Book> books = bookService.getAllBooks(); 
        model.addAttribute("books", books);
        model.addAttribute("user", user);
        model.addAttribute("loyaltyPoints", user.getLoyaltyPoints());
        return "home";
    }
    
    /**
    @GetMapping("/administrator")
	public String administrator(Model model) {
    	List<UserOrder> purchases = userOrderService.getAllUserOrders();
    	model.addAttribute("purchases", purchases);
		return "administrator";
	}
	**/
    
    @GetMapping("/register")
	public String register() {
		return "register";
	}
    
    @GetMapping("/review")
    public String review(@RequestParam Long bookId, Model model) {
        Book book = bookService.getBookById(bookId);
        model.addAttribute("book", book);
        return "review";
    }

       
    
	@GetMapping("/administrator")
	public String administrator(Model model) {
		List<Book> books = bookService.getAllBooks(); 
        model.addAttribute("books", books);
		return "administrator";
	}
	
	@GetMapping("/")
	public String login() {
		return "login";
	}
	
	@GetMapping("/book_form")
	public String bookForm() {
		return "book_form";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate(); 
	    return "redirect:/";
	}

}
