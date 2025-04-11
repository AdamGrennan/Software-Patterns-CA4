package com.example.demo.service;

import com.example.demo.model.User;

import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface UserService {
	List<User> getAllUsers();

	User getUserById(Long id);
	
	User addUser(User user);
	
	String login(String email, String password, HttpSession session);

	User updateUser(Long id, User user);

	void deleteUser(Long id);

}