package com.skropotov.crm.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skropotov.crm.models.User;
import com.skropotov.crm.models.Role;
import com.skropotov.crm.models.Status;
import com.skropotov.crm.repositories.RoleRepository;
import com.skropotov.crm.repositories.UserRepository;
import com.skropotov.crm.transfers.UserDto;

@Service
public class UsersServiceImpl implements UsersService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	private void fillUser(User user, UserDto userDto) {
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setUsername(userDto.getUsername());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setStatus(userDto.getStatus());
		
		Set<Role> roles = userDto.getRoles().stream()
			.map(s -> roleRepository.findByName(s).orElseThrow(() -> new EntityNotFoundException("Role " + s + " not found")))
			.collect(Collectors.toSet());
		
		user.setRoles(roles);
	}
	
	@Override
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User findOne(Long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
	}

	@Override
	public void deleteUser(Long userId) {
		User user = findOne(userId);
		if (user.getStatus() == Status.DELETED) {
			throw new PersistenceException("User already deleted");
		}
		user.setStatus(Status.DELETED);
		userRepository.save(user);
	}

	@Override
	public void createUser(UserDto userDto) {
		User user = new User();
		fillUser(user, userDto);
		userRepository.save(user);
	}
	
	@Override
	public void editUser(Long userId, UserDto userDto) {
		User savedUser = findOne(userId);
		User user = new User();
		user.setId(userId);
		user.setCreatedBy(savedUser.getCreatedBy());
		user.setCreated(savedUser.getCreated());
		fillUser(user, userDto);
		user.setVersion(userDto.getVersion());
		userRepository.save(user);
	}

	@Override
	public List<User> findNonDeletedUsers() {
		return userRepository.findNonDeletedUsers();
	}

	@Override
	public void activateUser(Long userId) {
		User user = findOne(userId);
		if (user.getStatus() == Status.ACTIVE) {
			throw new PersistenceException("User already active");
		}
		user.setStatus(Status.ACTIVE);
		userRepository.save(user);
	}

	@Override
	public void inactivateUser(Long userId) {
		User user = findOne(userId);
		if (user.getStatus() == Status.INACTIVE) {
			throw new PersistenceException("User already inactive");
		}
		user.setStatus(Status.INACTIVE);
		userRepository.save(user);
	}

}
