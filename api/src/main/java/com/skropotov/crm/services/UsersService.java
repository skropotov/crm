package com.skropotov.crm.services;

import java.util.List;

import com.skropotov.crm.models.User;
import com.skropotov.crm.transfers.UserDto;

public interface UsersService {
    List<User> findAll();
    List<User> findNonDeletedUsers();

    User findOne(Long userId);
    
    void deleteUser(Long userId);
    
    void createUser(UserDto userDto);
    
    void activateUser(Long userId);
    
    void inactivateUser(Long userId);
    
    void editUser(Long userId, UserDto userDto);
}
