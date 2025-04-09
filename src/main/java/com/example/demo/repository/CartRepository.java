package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Cart;
import com.example.demo.model.User;

public interface CartRepository extends JpaRepository<Cart,Long>{
	
	Cart findByUser(User user);
}
