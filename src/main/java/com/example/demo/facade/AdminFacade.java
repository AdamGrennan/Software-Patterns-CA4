package com.example.demo.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.service.BookService;
import com.example.demo.service.UserService;
import com.example.demo.model.Book;
import com.example.demo.model.User;
import com.example.demo.proxy.AdminProxy;
import com.example.demo.proxy.IAdminAccess;

@Component
public class AdminFacade {

    private final BookService bookService;
    private final UserService userService;

    public AdminFacade(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    public boolean isAuthorized(User user) {
        if (user == null) return false;
        IAdminAccess access = new AdminProxy(user);
        return !"unauthorized".equals(access.performAction());
    }


    public void addBook(Book book) {
        bookService.addBook(book);
    }

    public void updateBook(Long id, Book updatedBook) {
        bookService.updateBook(id, updatedBook);
    }

    public Book getBookById(Long id) {
        return bookService.getBookById(id);
    }
    
    public List<Book>getAllBooks() {
        return bookService.getAllBooks();
    }

    public List<User> getAllCustomers() {
        return userService.getAllCustomers();
    }
    
    public void simulateStock(Long bookId, int quantity) {
        bookService.increaseStock(bookId, quantity);
    }

}
