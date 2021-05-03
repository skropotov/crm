package com.skropotov.crm.transfers;

import com.skropotov.crm.models.Product;
import com.skropotov.crm.models.Status;

public class ProductDto {
	private Long productId;
	private String name;
	private String description;
	private Double price;
	private String vendorCode;
	private String barCode;
	private Status status;
	private int version;
	
	public static ProductDto from(Product product) {
		Double price = product.getPrice().doubleValue() / 100;
		return new ProductDto(product.getId(), product.getName(), product.getDescription(), price, product.getVendorCode(),
				product.getBarCode(), product.getStatus(), product.getVersion());
	}
	
	public ProductDto(Long productId, String name, String description, Double price, String vendorCode, String barCode,
			Status status, int version) {
		this.productId = productId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.vendorCode = vendorCode;
		this.barCode = barCode;
		this.status = status;
		this.version = version;
	}
	
	public ProductDto() {
	}

	public Product toProduct() {
		Long price = Math.round(this.price * 100);
		Product product = new Product();
		
		product.setName(this.name);
		product.setDescription(this.description);
		product.setPrice(price);
		product.setVendorCode(this.vendorCode);
		product.setBarCode(this.barCode);
		product.setStatus(this.status);
		
		return product;
	}
	
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
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
