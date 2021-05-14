package com.skropotov.crm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.skropotov.crm.models.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
	@Query(value = "select * from orders where status in ('ACTIVE', 'INACTIVE')", nativeQuery = true)
	List<Order> findNonDeletedOrders();
}
