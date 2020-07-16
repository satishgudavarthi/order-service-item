package com.orderitem.service;

import com.orderitem.entity.OrderItem;

public interface OrderItemService {

	public OrderItem createOrder(OrderItem order);
	
	public OrderItem fetchOrder(Integer productCode);
	
	public OrderItem updateOrder(OrderItem order);
}
