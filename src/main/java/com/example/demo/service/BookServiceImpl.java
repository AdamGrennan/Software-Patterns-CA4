package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.builder.BookBuilder;
import com.example.demo.builder.BookDirector;
import com.example.demo.builder.ConcreteBookBuilder;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Book> getAllBooks() {
		return null;
	}

	@Override
	public Book getBookById(Long id) {
		return null;
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
	    		book.getPrice()
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

}
