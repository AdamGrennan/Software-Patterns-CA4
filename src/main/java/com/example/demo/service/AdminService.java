package com.example.demo.service;

import com.example.demo.model.Book;

public interface AdminService {
	Book updateBook(Long id, Book book);
	void simulateStock(Long bookId, int quantity);
}
