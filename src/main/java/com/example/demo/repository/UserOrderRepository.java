package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.UserOrder;
import com.example.demo.model.User;

public interface UserOrderRepository extends JpaRepository<UserOrder,Long> {
	
	List<UserOrder> findByUser(User user);
}
