package com.skropotov.crm.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skropotov.crm.services.UsersService;
import com.skropotov.crm.transfers.UserDto;
import static com.skropotov.crm.transfers.UserDto.from;

@RestController
@CrossOrigin
public class UsersController {
	@Autowired
	UsersService userService;
	
	@GetMapping("/users")
	public List<UserDto> getUsers() {
		return userService.findNonDeletedUsers().stream()
				.map(x -> UserDto.from(x))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/users/{user-id}")
	public UserDto getUser(@PathVariable("user-id") Long userId) {
		return from(userService.findOne(userId));
	}
	
	@DeleteMapping("/users/{user-id}")
	public void deleteUser(@PathVariable("user-id") Long userId) {
		userService.deleteUser(userId);
	}
	
	@PostMapping("/users")
	public void createUser(@RequestBody UserDto userDto) {
		userService.createUser(userDto);
	}
	
	@PutMapping("/users/{user-id}")
	public void editUser(@PathVariable("user-id") Long userId, @RequestBody UserDto userDto) {
		userService.editUser(userId, userDto);
	}
}
