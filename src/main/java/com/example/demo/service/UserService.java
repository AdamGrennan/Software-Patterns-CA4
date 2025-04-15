package com.example.demo.service;

import com.example.demo.model.User;

import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface UserService {
	List<User> getAllUsers();

	User getUserById(Long id);
	
	User addUser(User user);
	
	User login(String email, String password);

	User updateUser(Long id, User user);

	void deleteUser(Long id);
	
	List<User> getAllCustomers();

}