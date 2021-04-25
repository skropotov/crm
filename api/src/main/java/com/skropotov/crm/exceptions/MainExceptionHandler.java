package com.skropotov.crm.exceptions;

import java.sql.SQLIntegrityConstraintViolationException;

import javax.persistence.EntityNotFoundException;

import org.hibernate.StaleObjectStateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.skropotov.crm.models.User;

@ControllerAdvice
public class MainExceptionHandler {
	
	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorDTO handleEntityNotFound(EntityNotFoundException e) {
		if (e.getMessage().equals("User not found")) {
			return new ErrorDTO("USER", e.getMessage());
		}
		return new ErrorDTO("OBJECT", e.getMessage());
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorDTO handleSQLIntegrityConstraint(SQLIntegrityConstraintViolationException e) {
		if (e.getMessage().contains(User.usernameConstraintName)) {
			return new ErrorDTO("USER", "Username already exists");
		}
		return new ErrorDTO("OBJECT", e.getMessage());
	}
	
	@ExceptionHandler(StaleObjectStateException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorDTO handleStaleObjectState(StaleObjectStateException e) {
		return new ErrorDTO("OBJECT_STALE", e.getMessage());
	}
}
