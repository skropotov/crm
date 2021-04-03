package com.skropotov.crm.models;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
@AttributeOverride(column = @Column(name = "role_id"), name = "id")
public class Role extends BaseEntity implements GrantedAuthority {

	private static final long serialVersionUID = -5207524477801358547L;

	@Column(unique = true)
	@Size(min = 2)
	private String name;
	
	@Transient
	private Set<User> users;

	
	public Role() {
		this.status = Status.ACTIVE;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String getAuthority() {
		return getName();
	}

	@Override
	public String toString() {
		return "Role [name=" + name + "]";
	}
}
