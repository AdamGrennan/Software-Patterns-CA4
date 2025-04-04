package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.CartItem;
import com.example.demo.model.UserOrder;
import com.example.demo.model.User;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

	List<UserOrder> findByUser(User user);
}
