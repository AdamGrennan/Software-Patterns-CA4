package com.example.demo.strategy;

import com.example.demo.model.Book;
import java.util.List;

public class BookSorter {
    private Sorter strategy;

    public void setStrategy(Sorter strategy) {
        this.strategy = strategy;
    }

    public void sortBooks(List<Book> books, boolean ascending) {
        strategy.sort(books, ascending);
    }
}
