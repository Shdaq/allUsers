package com.example.usertask.controller;

import com.example.usertask.dto.UsersDto;
import com.example.usertask.exception.InvalidRoleException;
import com.example.usertask.exception.UserNotFoundException;
import com.example.usertask.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.*;

import java.util.*;

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
    public UsersDto getUserById(@PathVariable Integer userId) {
        return service.getUserById(userId);

    }

    @PutMapping("/users/{userId}")
    public UsersDto updateUser(@RequestBody UsersDto userDto, @PathVariable Integer userId) {
        return service.updateUser(userDto, userId);
    }

    @PostMapping("/users/{userId}")
    public UsersDto addRoles(@RequestBody Set<String> roles, @PathVariable Integer userId) {
        return service.addRoles(roles, userId);
    }

    @DeleteMapping ("/users/{userId}")
    public void deleteUserById(@PathVariable Integer userId){
        service.deleteUserById(userId);

    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidRoleException.class)
    public ResponseEntity<String> handleInvalidRole(InvalidRoleException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }


}

