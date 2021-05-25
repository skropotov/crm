package com.skropotov.crm.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skropotov.crm.services.OrderService;
import com.skropotov.crm.transfers.OrderDto;

@RestController
@CrossOrigin
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@GetMapping("/orders")
	public List<OrderDto> getOrders() {
		return orderService.findAllNonDeletedOrders().stream()
				.map(x -> OrderDto.from(x))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/orders/{order-id}")
	public OrderDto getOrder(@PathVariable("order-id") Long orderId) {
		return OrderDto.from(orderService.findOne(orderId));
	}
	
	@DeleteMapping("/orders/{order-id}")
	public void deleteOrder(@PathVariable("order-id") Long orderId) {
		orderService.deleteOrder(orderId);
	}
	
	@PostMapping("/orders")
	public void createOrder(@RequestBody OrderDto orderDto) {
		orderService.createOrder(orderDto);
	}
	
	@PutMapping("/orders/{order-id}")
	public void editOrder(@PathVariable("order-id") Long orderId, @RequestBody OrderDto orderDto) {
		orderService.editOrder(orderId, orderDto);
	}
}
