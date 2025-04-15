package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Book;

public interface BookService {
	List<Book> getAllBooks();

	Book getBookById(Long id);
	
	List<Book> getBooksByCategory(String category);
	
	List<Book> getBooksByAuthor(String author);
	
	List<Book> getBooksByPublisher(String publisher);
	
	List<Book> getBooksByTitle(String title);
	
	Book addBook(Book book);

	Book updateBook(Long id, Book book);

	void deleteBook(Long id);
	
	List<Book> sortBooks(String sortBy, boolean ascending);
	
	List<Book> searchBooks(String searchBy, String query);
	
	void increaseStock(Long bookId, int quantity);

}
