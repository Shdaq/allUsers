package com.example.usertask.service;

import com.example.usertask.dto.UsersDto;
import com.example.usertask.entity.UsersEntity;
import com.example.usertask.mapper.UserMapper;
import com.example.usertask.repository.UsersRepo;
import org.springframework.stereotype.Service;
import lombok.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsersRepo repo;
    private final UserMapper userMapper;

    public List<UsersDto> getAllUsers() {
        return repo.findAll()
                .stream()
                .map(userMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public UsersDto addUser(UsersDto userDto){
        UsersEntity user=userMapper.mapToUser(userDto);
        UsersEntity saved=repo.save(user);
        return userMapper.mapToDto(saved);

    }
    public UsersDto getUserById(Integer userId) {
        UsersEntity user= repo.findById(userId).orElseThrow(() -> new RuntimeException("User not found "));
        return userMapper.mapToDto(user);

    }

    public UsersDto updateUser(UsersDto userDto, Integer userId) {
        UsersEntity matchedUser = repo.findById(userId).orElseThrow(() -> new RuntimeException("User not found "));
        userMapper.updateUser(userDto, matchedUser);
        repo.save(matchedUser);
        return userMapper.mapToDto(matchedUser);

    }
}
