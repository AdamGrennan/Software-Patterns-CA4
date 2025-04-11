package com.example.demo.observer;

import com.example.demo.model.Book;
import com.example.demo.model.Review;

public class AverageRatingObserver implements ReviewObserver {
    @Override
    public void update(Book book, Review review) {
        double total = book.getAverageRating() * book.getNumberReviews();
        total += review.getRating();
        book.setNumberReviews(book.getNumberReviews() + 1);
        book.setAverageRating(total / book.getNumberReviews());
    }
}
