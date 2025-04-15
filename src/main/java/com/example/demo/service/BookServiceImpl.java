package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.builder.BookBuilder;
import com.example.demo.builder.BookDirector;
import com.example.demo.builder.ConcreteBookBuilder;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.strategy.BookSorter;
import com.example.demo.strategy.SortByAuthor;
import com.example.demo.strategy.SortByCategory;
import com.example.demo.strategy.SortByPrice;
import com.example.demo.strategy.SortByPublisher;
import com.example.demo.strategy.SortByTitle;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Book getBookById(Long id) {
		return bookRepository.findById(id).orElse(null);
	}

	@Override
	public Book addBook(Book book) {
	    BookBuilder builder = new ConcreteBookBuilder(
	    		book.getTitle(),
	    		book.getAuthor(),
	    		book.getPublisher(),
	    		book.getCategory(),
	    		book.getIsbn(),
	    		book.getImage(),
	    		book.getPrice(),
	    		book.getStock()
	        );

	        BookDirector director = new BookDirector(builder);
	        Book builtBook = director.constructBook();

	        return bookRepository.save(builtBook);
	}

	@Override
	public Book updateBook(Long id, Book book) {
		return null;
	}

	@Override
	public void deleteBook(Long id) {
		
	}

	@Override
	public List<Book> getBooksByCategory(String category) {
		return bookRepository.findByCategoryContainingIgnoreCase(category);
	}

	@Override
	public List<Book> getBooksByAuthor(String author) {
		return bookRepository.findByAuthor(author);
	}

	@Override
	public List<Book> getBooksByPublisher(String publisher) {
		return bookRepository.findByPublisher(publisher);
	}

	@Override
	public List<Book> getBooksByTitle(String title) {
		return bookRepository.findByTitleContainingIgnoreCase(title);
	}
	
	public List<Book> sortBooks(String sortBy, boolean ascending) {
		BookSorter sorter = new BookSorter();
	    
	    switch (sortBy) {
	        case "title":
	        	 sorter.setStrategy(new SortByTitle());
	            break;
	        case "author":
	        	sorter.setStrategy(new SortByAuthor());
	            break;
	        case "publisher":
	        	sorter.setStrategy(new SortByPublisher());
	            break;
	        case "category":
	        	sorter.setStrategy(new SortByCategory());
	            break;
	        case "price":
	        	sorter.setStrategy(new SortByPrice());
	            break;

	    }
	    List<Book> books = getAllBooks();
	    sorter.sortBooks(books, ascending);
	    return books;
	}
	
	public List<Book> searchBooks(String searchBy, String query) {
	    switch (searchBy.toLowerCase()) {
	        case "title":
	            return getBooksByTitle(query);
	        case "author":
	            return getBooksByAuthor(query);
	        case "publisher":
	            return getBooksByPublisher(query);
	        case "category":
	            return getBooksByCategory(query);
	        default:
	            return new ArrayList<>();
	    }
	}
	
	public void increaseStock(Long bookId, int quantity) {
	    Book book = bookRepository.findById(bookId).orElse(null);
	    book.setStock(book.getStock() + quantity);
	    bookRepository.save(book);
	}
}
