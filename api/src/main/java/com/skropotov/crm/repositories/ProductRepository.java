package com.skropotov.crm.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.skropotov.crm.models.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
	Optional<Product> findByName(String name);
	
	@Query(value = "select * from products where status in ('ACTIVE', 'INACTIVE')", nativeQuery = true)
	List<Product> findNonDeletedProducts();
	
}
