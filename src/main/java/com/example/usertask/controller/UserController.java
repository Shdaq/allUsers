package com.example.usertask.controller;

import com.example.usertask.dto.UserDto;
import com.example.usertask.service.UserService;
import org.springframework.web.bind.annotation.*;
import lombok.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return service.getAllUsers();

    }
    @PostMapping("/add")
    public UserDto addUser(@RequestBody UserDto userDto) {
        return service.addUser(userDto);

    }
    @GetMapping("/get/{userId}")
    public UserDto getUserById(@PathVariable Integer userId){
        return service.getUserById(userId);

    }

    @PutMapping("/update/{userId}")
    public UserDto updateUser(@RequestBody UserDto userDto,@PathVariable Integer userId){
        return service.updateUser(userDto,userId);
    }



}

