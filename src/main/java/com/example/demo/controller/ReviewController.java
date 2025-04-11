package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Book;
import com.example.demo.model.Review;
import com.example.demo.model.User;
import com.example.demo.service.BookService;
import com.example.demo.service.ReviewService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/review")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private BookService bookService;

	@PostMapping("/addReview")
	public String addReview(
	    @RequestParam Long bookId,
	    @RequestParam int rating,
	    @RequestParam String comment,
	    HttpSession session
	) {
	    User user = (User) session.getAttribute("user");
	    reviewService.addReview(bookId, rating, comment, user);
	    return "user_orders";
	}

	
	@GetMapping("/viewReviews")
	public String viewReviews(HttpSession session, Model model, @RequestParam Long bookId) {
		List<Review> reviews = reviewService.getReviews(bookId);
	    Book book = bookService.getBookById(bookId); 
	    model.addAttribute("reviews", reviews);
	    model.addAttribute("book", book);

	    return "item_reviews"; 
	}
}
