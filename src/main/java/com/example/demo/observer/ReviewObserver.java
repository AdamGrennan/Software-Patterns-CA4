package com.example.demo.observer;

import com.example.demo.model.Book;
import com.example.demo.model.Review;

public interface ReviewObserver {
    void update(Book book, Review review);
}
