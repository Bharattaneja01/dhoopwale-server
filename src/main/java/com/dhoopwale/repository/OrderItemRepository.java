package com.dhoopwale.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhoopwale.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long>{

}
