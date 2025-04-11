package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Book;
import com.example.demo.model.Review;
import com.example.demo.model.User;
import com.example.demo.observer.AverageRatingObserver;
import com.example.demo.observer.ReviewSubject;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private BookRepository bookRepository;

	@Override
	public void addReview(Long bookId, int rating, String comment, User user) {
	    Book book = bookRepository.findById(bookId).orElse(null);

	    Review review = new Review();
	    review.setRating(rating);
	    review.setComment(comment);
	    review.setBook(book);
	    review.setUser(user);

	    reviewRepository.save(review);

	    ReviewSubject subject = new ReviewSubject();
	    subject.attach(new AverageRatingObserver());
	    subject.setReview(book, review);

	    bookRepository.save(book);
	}
	
	@Override
	public List<Review> getReviews(Long bookId) {
	    Book book = bookRepository.findById(bookId).orElse(null);
	    return reviewRepository.findByBook(book);
	}


}
