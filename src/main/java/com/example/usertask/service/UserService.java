package com.example.usertask.service;

import com.example.usertask.dto.UserDto;

import java.util.List;

public interface UserService {
   List<UserDto> getAllUsers();
   UserDto addUser(UserDto userDto);

   UserDto getUserById(Integer userId);

   UserDto updateUser(UserDto userDto,Integer userId);
  
}
