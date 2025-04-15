package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	private BookRepository bookRepository;

	@Override
	public Book updateBook(Long id, Book book) {
		Book existingBook= bookRepository.findById(id).orElse(null);
		if (existingBook != null) {
			existingBook.setCategory(book.getCategory());
			existingBook.setAuthor(book.getAuthor());
			existingBook.setTitle(book.getTitle());
			existingBook.setPublisher(book.getPublisher());
			existingBook.setPrice(book.getPrice());
			existingBook.setIsbn(book.getIsbn());
			existingBook.setImage(book.getImage());
			existingBook.setStock(book.getStock());
			return bookRepository.save(existingBook);
		} else {
			return null;
		}
	}
	
}
