package com.skropotov.crm.transfers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.skropotov.crm.models.Order;
import com.skropotov.crm.models.Status;

public class OrderDto {
	private Long orderId;
	private String orderDate;
	private Long clientId;
	private String clientName;
	private Long productId;
	private String productName;
	private Long amount;
	private Status status;
	private int version;

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
	
	public static OrderDto from(Order order) {
		return new OrderDto(order.getId(), order.getOrderDate().format(formatter), order.getClient().getId(), order.getClient().toString(), 
				order.getProduct().getId(), order.getProduct().toString(), order.getAmount(), order.getStatus(), order.getVersion());
	}
	
	public OrderDto(Long orderId, String orderDate, Long clientId, String clientName, Long productId, String productName, Long amount, Status status,
			int version) {
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.clientId = clientId;
		this.clientName = clientName;
		this.productId = productId;
		this.productName = productName;
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

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
}
