package com.skropotov.crm.services;

import java.util.List;

import com.skropotov.crm.models.Order;
import com.skropotov.crm.transfers.OrderDto;

public interface OrderService {
	List<Order> findAllNonDeletedOrders();
	Order findOne(Long orderId);
	
	void deleteOrder(Long orderId);
	void createOrder(OrderDto orderDto);
	void editOrder(Long orderId, OrderDto orderDto);
}
