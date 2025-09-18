package com.example.usertask.controller;

import com.example.usertask.dto.UsersDto;
import com.example.usertask.service.UserServiceImpl;
import org.springframework.web.bind.annotation.*;
import lombok.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl service;

    @GetMapping("/users")
    public List<UsersDto> getAllUsers() {
        return service.getAllUsers();

    }
    @PostMapping("/users")
    public UsersDto addUser(@RequestBody UsersDto userDto) {
        return service.addUser(userDto);

    }
    @GetMapping("/users/{userId}")
    public UsersDto getUserById(@PathVariable Integer userId){
        return service.getUserById(userId);

    }

    @PutMapping("/users/{userId}")
    public UsersDto updateUser(@RequestBody UsersDto userDto, @PathVariable Integer userId){
        return service.updateUser(userDto,userId);
    }



}

