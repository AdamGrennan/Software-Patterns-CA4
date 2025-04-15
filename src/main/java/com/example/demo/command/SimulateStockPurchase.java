package com.example.demo.command;

import com.example.demo.service.BookService;

public class SimulateStockPurchase implements StockCommand{
	
    private BookService bookService;
    private Long bookId;
    private int quantity;

    public SimulateStockPurchase(BookService bookService, Long bookId, int quantity) {
        this.bookService = bookService;
        this.bookId = bookId;
        this.quantity = quantity;
    }

    @Override
    public void execute() {
        bookService.increaseStock(bookId, quantity);
    }
}
