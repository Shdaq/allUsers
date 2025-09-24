package com.example.usertask.service;

import com.example.usertask.dto.UsersDto;
import com.example.usertask.entity.RolesEntity;
import com.example.usertask.entity.UsersEntity;
import com.example.usertask.exception.InvalidRoleException;
import com.example.usertask.exception.UserNotFoundException;
import com.example.usertask.mapper.UserMapper;
import com.example.usertask.repository.RolesRepo;
import com.example.usertask.repository.UsersRepo;
import org.springframework.stereotype.Service;
import lombok.*;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsersRepo userRepo;
    private final RolesRepo rolesRepo;

    private final UserMapper userMapper;

    public List<UsersDto> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(userMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public UsersDto addUser(UsersDto userDto){
        UsersEntity user=userMapper.mapToUser(userDto);
        UsersEntity saved=userRepo.save(user);
        return userMapper.mapToDto(saved);

    }
    public UsersDto getUserById(Integer userId) {
        UsersEntity user= userRepo.findById(userId).orElseThrow(() ->  new UserNotFoundException("User not found with ID: " + userId));
        return userMapper.mapToDto(user);

    }

    public UsersDto updateUser(UsersDto userDto, Integer userId) {
        UsersEntity matchedUser = userRepo.findById(userId).orElseThrow(() ->  new UserNotFoundException("User not found with ID: " + userId));
        userMapper.updateUser(userDto, matchedUser);
        userRepo.save(matchedUser);
        return userMapper.mapToDto(matchedUser);

    }

    public UsersDto addRoles(Set<String> roles, Integer userId) {
        UsersEntity matchedUser = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        Set<RolesEntity> newRoles=new HashSet<>();

        for(String roleName:roles){
            String normalized = roleName.toLowerCase();
            RolesEntity role=rolesRepo.findByRole(normalized).orElseThrow(() -> new InvalidRoleException(roleName+ " is not a valid role"));
            newRoles.add(role);
        }
        Set<RolesEntity> prevRoles=matchedUser.getRoles();
        prevRoles.addAll(newRoles);
        matchedUser.setRoles(prevRoles);

        userRepo.save(matchedUser);
        return userMapper.mapToDto(matchedUser);


    }
}
