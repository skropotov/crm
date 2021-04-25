package com.skropotov.crm.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.skropotov.crm.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findByUsername(String username);

	@Query(value = "select * from users where status in ('ACTIVE', 'INACTIVE')", nativeQuery = true)
	List<User> findNonDeletedUsers();
}
