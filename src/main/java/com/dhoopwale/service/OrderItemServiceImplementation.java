package com.dhoopwale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhoopwale.model.OrderItem;
import com.dhoopwale.repository.OrderItemRepository;

@Service
public class OrderItemServiceImplementation implements OrderItemService{

	@Autowired
	private OrderItemRepository orderItemRepository;
	
	
	
	@Override
	public OrderItem createOrderItem(OrderItem orderItem) {
		
		
		return orderItemRepository.save(orderItem);
	}

}
