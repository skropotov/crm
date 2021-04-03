package com.skropotov.crm.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.skropotov.crm.models.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	Optional<Role> findByName(String name);
}
