package com.skropotov.crm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.skropotov.crm.models.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {
	@Query(value = "select * from clients where status in ('ACTIVE', 'INACTIVE')", nativeQuery = true)
	List<Client> findNonDeletedClients();
}
