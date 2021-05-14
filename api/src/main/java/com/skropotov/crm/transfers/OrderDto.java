package com.skropotov.crm.transfers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.skropotov.crm.models.Client;
import com.skropotov.crm.models.Order;
import com.skropotov.crm.models.Product;
import com.skropotov.crm.models.Status;

public class OrderDto {
	private Long orderId;
	private String orderDate;
	private Long clientId;
	private Long productId;
	private Long amount;
	private Status status;
	private int version;

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy"); 
	
	public static OrderDto from(Order order) {
		return new OrderDto(order.getId(), order.getOrderDate().format(formatter), order.getClient().getId(), order.getProduct().getId(),
				order.getAmount(), order.getStatus(), order.getVersion());
	}
	
	public OrderDto(Long orderId, String orderDate, Long clientId, Long productId, Long amount, Status status,
			int version) {
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.clientId = clientId;
		this.productId = productId;
		this.amount = amount;
		this.status = status;
		this.version = version;
	}

	public OrderDto() {
	}
	
	public Order toOrder() {
		Order order = new Order();
		
		order.setAmount(this.amount);
		order.setOrderDate(LocalDate.parse(this.orderDate, formatter));
		order.setStatus(this.status);
		
		return order;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
}
