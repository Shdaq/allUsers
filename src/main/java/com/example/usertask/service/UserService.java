package com.example.usertask.service;

import com.example.usertask.dto.UserDto;
import com.example.usertask.model.User;
import com.example.usertask.mapper.UserMapper;
import com.example.usertask.repository.UserRepo;
import org.springframework.stereotype.Service;
import lombok.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements ServiceInterface {

    private final UserRepo repo;
    private final UserMapper userMapper;

    public List<UserDto> getAllUsers() {
        return repo.findAll()
                .stream()
                .map(userMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public UserDto addUser(UserDto userDto){
        User user=userMapper.mapToUser(userDto);
        User saved=repo.save(user);
        return userMapper.mapToDto(saved);

    }
    public UserDto getUserById(Integer userId) {
        User user= repo.findById(userId).orElseThrow(() -> new RuntimeException("User not found "));
        return userMapper.mapToDto(user);

    }

    public UserDto updateUser(UserDto userDto,Integer userId) {
        User matchedUser = repo.findById(userId).orElseThrow(() -> new RuntimeException("User not found "));
        matchedUser.setFirstName(userDto.getFirstName());
        matchedUser.setLastName(userDto.getLastName());
        matchedUser.setDob(userDto.getDob());
        repo.save(matchedUser);
        return userMapper.mapToDto(matchedUser);

    }
}
