package com.skropotov.crm.transfers;

import com.skropotov.crm.models.Client;
import com.skropotov.crm.models.Status;

public class ClientDto {
	private Long clientId;
	private String name;
	private Long managerId;
	private String managerName;
	private String phone;
	private String email;
	private String region;
	private String address;
	private Status status;
	private int version;

	public static ClientDto from(Client client) {
		return new ClientDto(client.getId(), client.getName(), client.getManager().getId(), client.getManager().toString(), client.getPhone(), 
				client.getEmail(), client.getRegion(), client.getAddress(), client.getStatus(), client.getVersion());
	}

	public ClientDto(Long clientId, String name, Long managerId, String managerName, String phone, String email, String region, String address,
			Status status, int version) {
		this.clientId = clientId;
		this.name = name;
		this.managerId = managerId;
		this.managerName = managerName;
		this.phone = phone;
		this.email = email;
		this.region = region;
		this.address = address;
		this.status = status;
		this.version = version;
	}

	public ClientDto() {
	}

	public Client toClient() {
		Client client = new Client();
		
		client.setName(this.name);
		client.setAddress(this.address);
		client.setEmail(this.email);
		client.setPhone(this.phone);
		client.setRegion(this.region);
		client.setStatus(this.status);
		
		return client;
	}
	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
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

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
}
