package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.OrderItem;
import com.example.demo.repository.OrderItemRepository;

public class OrderItemServiceImpl implements OrderItemService{
	
	@Autowired
	private OrderItemRepository itemRepository;

	@Override
	public OrderItem addItem(OrderItem item) {
		return itemRepository.save(item);
	}

}
