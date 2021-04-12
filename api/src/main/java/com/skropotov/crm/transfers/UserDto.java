package com.skropotov.crm.transfers;

import java.util.ArrayList;
import java.util.List;

import com.skropotov.crm.models.Status;
import com.skropotov.crm.models.User;

public class UserDto {
	private Long userId;
	private String username;
	private String firstName;
	private String lastName;
	private String password;
	private Status status;
	private Integer version;
	private List<String> roles;

	public static UserDto from(User user) {
		ArrayList<String> roles = new ArrayList<>();
		user.getRoles().stream().forEach(x -> roles.add(x.getAuthority()));
		return new UserDto(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), "", user.getStatus(), user.getVersion(), roles);
	}
	public UserDto(Long userId, String username, String firstName, String lastName, String password, Status status, Integer version, List<String> roles) {
		this.userId = userId;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.roles = roles;
		this.password = password;
		this.status = status;
		this.version = version;
	}

	public UserDto() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Integer getVersion() {
		return version;
	}
	
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
