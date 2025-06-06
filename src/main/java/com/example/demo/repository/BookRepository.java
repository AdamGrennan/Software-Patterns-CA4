package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Book;

public interface BookRepository extends JpaRepository<Book,Long> {

	List<Book> findByAuthor(String author);
	List<Book> findByPublisher(String publisher);
	List<Book> findByTitleContainingIgnoreCase(String title);
	List<Book> findByCategoryContainingIgnoreCase(String category);


}
