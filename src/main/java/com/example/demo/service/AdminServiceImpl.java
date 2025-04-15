package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Book;
import com.example.demo.command.AdminInvoker;
import com.example.demo.command.SimulateStockPurchase;
import com.example.demo.command.StockCommand;

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	private BookService bookService;
	
	@Autowired
	private AdminInvoker adminInvoker;

	@Override
	public Book updateBook(Long id, Book book) {
		Book existingBook= bookService.getBookById(id);
		if (existingBook != null) {
			existingBook.setCategory(book.getCategory());
			existingBook.setAuthor(book.getAuthor());
			existingBook.setTitle(book.getTitle());
			existingBook.setPublisher(book.getPublisher());
			existingBook.setPrice(book.getPrice());
			existingBook.setIsbn(book.getIsbn());
			existingBook.setImage(book.getImage());
			return bookService.addBook(existingBook);
		} else {
			return null;
		}
	}
	
	public void simulateStock(Long bookId, int quantity) {
	    StockCommand command = new SimulateStockPurchase(bookService, bookId, quantity);
	    adminInvoker.takeCommand(command);
	    adminInvoker.placeCommands();
	}

	
}
