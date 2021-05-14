package com.skropotov.crm.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skropotov.crm.models.Client;
import com.skropotov.crm.models.Order;
import com.skropotov.crm.models.Product;
import com.skropotov.crm.models.Status;
import com.skropotov.crm.repositories.ClientRepository;
import com.skropotov.crm.repositories.OrderRepository;
import com.skropotov.crm.repositories.ProductRepository;
import com.skropotov.crm.transfers.OrderDto;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired 
	ClientService clientService;
	
	@Autowired
	ProductService productService;
	
	@Override
	public List<Order> findAllNonDeletedOrders() {
		return orderRepository.findNonDeletedOrders();
	}

	@Override
	public Order findOne(Long orderId) {
		return orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("Order not found"));
	}

	@Override
	public void deleteOrder(Long orderId) {
		Order order = findOne(orderId);
		if(order.getStatus() == Status.DELETED) {
			throw new PersistenceException("Order already deleted");
		}
		order.setStatus(Status.DELETED);
		orderRepository.save(order);
	}

	@Override
	public void createOrder(OrderDto orderDto) {
		Client client = clientService.findOne(orderDto.getClientId());
		Product product = productService.findOne(orderDto.getProductId());
		
		Order order = orderDto.toOrder();
		order.setClient(client);
		order.setProduct(product);
		orderRepository.save(order);
	}

	@Override
	public void editOrder(Long orderId, OrderDto orderDto) {
		Client client = clientService.findOne(orderDto.getClientId());
		Product product = productService.findOne(orderDto.getProductId());
		Order savedOrder = findOne(orderId);
		
		Order order = orderDto.toOrder();
		order.setId(orderId);
		order.setCreatedBy(savedOrder.getCreatedBy());
		order.setCreated(savedOrder.getCreated());
		order.setVersion(orderDto.getVersion());
		order.setClient(client);
		order.setProduct(product);
		
		orderRepository.save(order);
	}
}
