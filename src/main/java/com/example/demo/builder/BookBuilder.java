package com.example.demo.builder;

import com.example.demo.model.Book;

public interface BookBuilder {
	
    void buildTitle();
    void buildAuthor();
    void buildPublisher();
    void buildPrice();
    void buildCategory();
    void buildIsbn();
    void buildImage();
    Book getResult();

}
