package com.example.demo.builder;

import com.example.demo.model.Book;

public class ConcreteBookBuilder implements BookBuilder {
    private Book book;

    private String title;
    private String author;
    private String publisher;
    private String category;
    private String isbn;
    private String imageUrl;
    private double price;

    public ConcreteBookBuilder(String title, String author, String publisher,
                                String category, String isbn, String imageUrl, double price) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.category = category;
        this.isbn = isbn;
        this.imageUrl = imageUrl;
        this.price = price;
        this.book = new Book();
    }

    @Override
    public void buildTitle() {
        book.setTitle(title);
    }

    @Override
    public void buildAuthor() {
        book.setAuthor(author);
    }

    @Override
    public void buildPublisher() {
        book.setPublisher(publisher);
    }

    @Override
    public void buildPrice() {
        book.setPrice(price);
    }

    @Override
    public void buildCategory() {
        book.setCategory(category);
    }

    @Override
    public void buildIsbn() {
        book.setIsbn(isbn);
    }

    @Override
    public void buildImage() {
        book.setImage(imageUrl);
    }

    @Override
    public Book getResult() {
        return book;
    }
}
