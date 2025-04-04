package com.example.demo.strategy;

import java.util.List;

import com.example.demo.model.Book;

public interface Sorter {
public void sort(List<Book> books, boolean ascending);
}