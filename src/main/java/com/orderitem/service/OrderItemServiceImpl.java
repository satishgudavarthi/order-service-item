package com.orderitem.service;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderitem.dao.OrderItemDao;
import com.orderitem.entity.OrderItem;
import com.orderitem.exception.ProductException;

@Service
public class OrderItemServiceImpl implements OrderItemService {
	
	@Autowired
	private OrderItemDao orderItemDao;

	@Transactional(value=TxType.REQUIRES_NEW,rollbackOn=Exception.class)
	@Override
	public OrderItem createOrder(OrderItem order) {
		return orderItemDao.save(order);
	}

	@Override
	public OrderItem fetchOrder(Integer productCode) {
		
		Optional<OrderItem> orderItem = orderItemDao.findById(productCode);
		if(!orderItem.isPresent()){
			throw new ProductException("E1004", "No Order-Item Found");
		}
		return orderItem.get();
		//return orderItemDao.fetchByProductCode(productCode);
	}

	@Override
	public OrderItem updateOrder(OrderItem order) {
		// TODO Auto-generated method stub
		return null;
	}

}
