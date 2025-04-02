package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int rating;
	private String comment;
	
	@ManyToOne
	private User user;

	@ManyToOne
	private Book book;

	public Review() {
		
	}
	
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
}
