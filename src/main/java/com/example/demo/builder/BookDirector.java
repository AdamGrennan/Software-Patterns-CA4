package com.example.demo.builder;

import com.example.demo.model.Book;

public class BookDirector {
    private BookBuilder builder;

    public BookDirector(BookBuilder builder) {
        this.builder = builder;
    }

    public Book constructBook() {
        builder.buildTitle();
        builder.buildAuthor();
        builder.buildPublisher();
        builder.buildPrice();
        builder.buildCategory();
        builder.buildIsbn();
        builder.buildImage();
        builder.buildStock();
        return builder.getResult();
    }
}
