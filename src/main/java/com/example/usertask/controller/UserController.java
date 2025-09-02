package com.example.usertask.controller;

import com.example.usertask.dto.UserDto;
import com.example.usertask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return service.getAllUsers();

    }


}

