package com.skropotov.crm.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.skropotov.crm.models.Token;


public interface TokenRepository extends CrudRepository<Token, Long> {
	Optional<Token> findOneByValue(String value); 
}
