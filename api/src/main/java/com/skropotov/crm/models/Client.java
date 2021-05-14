package com.skropotov.crm.models;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "clients", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}, name = Client.clientNameConstraintName)})
@AttributeOverride(column = @Column(name = "client_id"), name = "id")
public class Client extends BaseEntity {
	public final static String clientNameConstraintName = "client_name_uk";
	
	@Column
	private String name;
	
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "manager_id") 
	private User manager;
	
	@Column 
	private String phone;
	
	@Column
	private String email;
	
	@Column
	private String region;
	
	@Column
	private String address;
	
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Order> orders;

	public Client() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
