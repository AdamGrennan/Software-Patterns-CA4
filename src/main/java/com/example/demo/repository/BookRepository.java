package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Book;

public interface BookRepository extends JpaRepository<Book,Long> {

	List<Book> findByTitle(String title);
	List<Book> findByAuthor(String author);
	List<Book> findByCategory(String category);
	List<Book> findByPublisher(String publisher);

}
