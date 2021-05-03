package com.skropotov.crm.models;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

@Entity
@Table(name="products", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "description"}, name = Product.productNameConstraintName)})
@AttributeOverride(column = @Column(name = "product_id"), name = "id")
public class Product extends BaseEntity {
	public static final String productNameConstraintName = "product_name_uk";

	@Column
	private String name;
	
	@Column
	private String description;
	
	@Column(name="vendor_code")
	private String vendorCode;
	
	@Column
	private Long price;
	
	@Column(name="ean")
	@Size(min=13, max=13)
	private String barCode;

	public Product() {
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

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
}
