package com.example.usertask.service;

import com.example.usertask.dto.UsersDto;

import java.util.List;

public interface UserService {
   List<UsersDto> getAllUsers();
   UsersDto addUser(UsersDto userDto);

   UsersDto getUserById(Integer userId);

   UsersDto updateUser(UsersDto userDto, Integer userId);
  
}
