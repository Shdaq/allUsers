package com.example.userTask.controller;

import com.example.userTask.model.User;
import com.example.userTask.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@RestController
public class userController {
    @Autowired
    private UserRepo repo;

    @GetMapping("users")
    public List<User> getAllUsers() {
        return repo.findAll();

    }
    @GetMapping("/test")
    public String test() {
        long count = repo.count();
        return "Users count in DB: " + count;
    }

}

