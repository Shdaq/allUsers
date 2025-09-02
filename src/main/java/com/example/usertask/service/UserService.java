package com.example.usertask.service;

import com.example.usertask.dto.UserDto;
import com.example.usertask.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    public UserRepo repo;
    public List<UserDto> getAllUsers() {
        return repo.findAll()
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList());

    }
}
