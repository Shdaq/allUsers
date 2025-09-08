package com.example.usertask.service;

import com.example.usertask.dto.UserDto;
import com.example.usertask.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
public class UserService implements ServiceInterface { // make this and interface and create the implementation class.
    @Autowired
    public UserRepo repo;
   //private final UserRepo repo;

    public List<UserDto> getAllUsers() {
        return repo.findAll()
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList());

    }
}
