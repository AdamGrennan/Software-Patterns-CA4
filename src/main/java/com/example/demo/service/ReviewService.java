package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Review;
import com.example.demo.model.User;

public interface ReviewService {

	void addReview(Long bookId, int rating, String comment, User user);
	List<Review> getReviews(Long bookId);
}
