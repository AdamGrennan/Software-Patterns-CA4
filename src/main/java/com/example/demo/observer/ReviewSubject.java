package com.example.demo.observer;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Book;
import com.example.demo.model.Review;

public class ReviewSubject {
    private List<ReviewObserver> observers = new ArrayList<>();
    private Review review;
    private Book book;

    public void attach(ReviewObserver o) {
        observers.add(o);
    }

    public void setReview(Book book, Review review) {
        this.book = book;
        this.review = review;
        notifyAllObservers();
    }

    private void notifyAllObservers() {
        for (ReviewObserver o : observers) {
            o.update(book, review);
        }
    }
}
