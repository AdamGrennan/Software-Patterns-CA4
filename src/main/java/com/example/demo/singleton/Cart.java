package com.example.demo.singleton;

import com.example.demo.model.Book;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static final Cart INSTANCE = new Cart();

    private List<Book> items = new ArrayList<>();

    private Cart() {
    }

    public static Cart getInstance() {
        return INSTANCE;
    }

    public void addBook(Book book) {
        items.add(book);
    }

    public void removeBook(Book book) {
        items.remove(book);
    }

    public List<Book> getItems() {
        return items;
    }

    public void clearCart() {
        items.clear();
    }

    public double getTotalPrice() {
    	double total = 0;
    	for(Book b: items) {
    		total =+ b.getPrice();
    	}
    	return total;
    }
}
