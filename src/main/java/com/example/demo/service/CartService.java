package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.singleton.Cart;

public interface CartService {

	Cart addToCart(Book book);
}
