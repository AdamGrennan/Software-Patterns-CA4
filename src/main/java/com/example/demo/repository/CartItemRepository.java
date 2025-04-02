package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.CartItem;
import com.example.demo.model.Order;
import com.example.demo.model.User;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

	List<Order> findByUser(User user);
}
