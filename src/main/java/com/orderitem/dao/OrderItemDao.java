package com.orderitem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orderitem.entity.OrderItem;

public interface OrderItemDao extends JpaRepository<OrderItem, Integer> {

	//public Order fetchByProductCode(String productCode);
}
