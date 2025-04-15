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

	public void simulateStock(Long bookId, int quantity) {
	    StockCommand command = new SimulateStockPurchase(bookService, bookId, quantity);
	    adminInvoker.takeCommand(command);
	    adminInvoker.placeCommands();
	}

	
}
