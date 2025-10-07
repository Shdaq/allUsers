package com.example.usertask.service;

import com.example.usertask.dto.UsersDto;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<UsersDto> getAllUsers();

    UsersDto addUser(UsersDto userDto);

    UsersDto getUserById(Integer userId);

    UsersDto updateUser(UsersDto userDto, Integer userId);

    UsersDto addRoles(Set<String> roles, Integer userId);
     void deleteUserById(Integer userId);
}
