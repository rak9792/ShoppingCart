package com.packt.webstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.packt.webstore.domain.Order;
import com.packt.webstore.domain.repository.OrderRepository;
import com.packt.webstore.service.OrderService;

public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public Long saveOrder(Order order) {
	return orderRepository.saveOrder(order);
	}

}
