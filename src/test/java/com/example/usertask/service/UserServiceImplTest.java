package com.example.usertask.service;

import com.example.usertask.dto.UsersDto;
import com.example.usertask.entity.RolesEntity;
import com.example.usertask.entity.UsersEntity;
import com.example.usertask.exception.InvalidRoleException;
import com.example.usertask.exception.UserNotFoundException;
import com.example.usertask.mapper.UserMapper;
import com.example.usertask.repository.RolesRepo;
import com.example.usertask.repository.UsersRepo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    UsersRepo userRepo;

    @Mock
    RolesRepo rolesRepe;

    @Mock
    UsersDto usersDto;
    @Mock
    UserMapper userMapper;
    @InjectMocks
    UserServiceImpl userService;

    private static UsersEntity user1 = null;
    private static UsersEntity user2 = null;

    private static UsersDto usersDto1 = null;
    private static UsersDto usersDto2 = null;


    @BeforeAll
    public static void init() {
        System.out.println("BeforeAll");
        user1 = new UsersEntity();
        user1.setUserId(1);
        user2 = new UsersEntity();
        usersDto1 = new UsersDto();
        usersDto1.setUserId(1);
        usersDto2 = new UsersDto();
        usersDto2.setUserId(1);

    }

    @BeforeEach
    public void initEachTest() {
        System.out.println("BeforeEach");


    }
    @AfterAll
    public static void destroy() {
        System.out.println("AfterAll");
    }

    @AfterEach
    public void cleanup() {
        System.out.println("AfterEach");
    }

    @Test
    void getAllUsersTest() {
        List<UsersEntity> users = Arrays.asList(user1, user2);
        List<UsersDto> usersDto = Arrays.asList(usersDto1, usersDto2);

        when(userRepo.findAll()).thenReturn(users);

        when(userMapper.mapToDto(user1)).thenReturn(usersDto1);
        when(userMapper.mapToDto(user2)).thenReturn(usersDto2);

        List<UsersDto> actualUsers = userService.getAllUsers();

        assertEquals(usersDto, actualUsers);

        verify(userRepo).findAll();
        verify(userMapper).mapToDto(user1);
        verify(userMapper).mapToDto(user2);


    }

    @Test
    void addUserTest() {

        UsersDto inputDto = new UsersDto();
        inputDto.setFirstName("Shahad");
        inputDto.setLastName("Alqasem");
        inputDto.setDob(LocalDate.of(2003, 01, 01));


        UsersEntity mappedToEntity = new UsersEntity();
        mappedToEntity.setFirstName("Shahad");
        mappedToEntity.setLastName("Alqasem,");
        mappedToEntity.setDob(LocalDate.of(2003, 01, 01));


        UsersEntity savedEntity = new UsersEntity();
        savedEntity.setUserId(1);
        savedEntity.setFirstName("Shahad");
        savedEntity.setLastName("Alqasem");
        savedEntity.setDob(LocalDate.of(2003, 01, 01));


        UsersDto expectedDto = new UsersDto();
        expectedDto.setUserId(1);
        expectedDto.setFirstName("Shahad");
        expectedDto.setLastName("Alqasem");
        expectedDto.setDob(LocalDate.of(2003, 01, 01));
        expectedDto.setFullName("Shahad Alqasem");

        when(userMapper.mapToUser(inputDto)).thenReturn(mappedToEntity);
        when(userRepo.save(mappedToEntity)).thenReturn(savedEntity);
        when(userMapper.mapToDto(savedEntity)).thenReturn(expectedDto);

        UsersDto addedUser = userService.addUser(inputDto);
        assertNotNull(addedUser);
        assertEquals(1, addedUser.getUserId());
        assertEquals("Shahad", addedUser.getFirstName());
        assertEquals("Alqasem", addedUser.getLastName());
        assertEquals(LocalDate.of(2003, 01, 01), addedUser.getDob());
        assertEquals("Shahad Alqasem", addedUser.getFullName());

        verify(userMapper).mapToUser(inputDto);
        verify(userRepo).save(mappedToEntity);
        verify(userMapper).mapToDto(savedEntity);
    }


    @Test
    void getUserById() {
        when(userRepo.findById(1)).thenReturn(Optional.of(user1));
        when(userMapper.mapToDto(user1)).thenReturn(usersDto1);

        UsersDto actualDto = userService.getUserById(1);

        assertEquals(usersDto1, actualDto);
        verify(userRepo).findById(1);
        verify(userMapper).mapToDto(user1);

    }

    @Test
    void DeleteUserByIdTest() {
        when(userRepo.findById(1)).thenReturn(Optional.of(user1));//mock find by id
        doNothing().when(userRepo).deleteById(1);
        userService.deleteUserById(1);
        verify(userRepo).deleteById(1); //checking if deleteById from the repo is called once
    }

    @Test
    void updateUser() {
        //input
        usersDto1.setFirstName("updatedFirstName");
        usersDto1.setLastName("updatedLastName");
        //existing
        user1.setFirstName("OldFirstName");
        user1.setLastName("OldLastName");
        //expected
        usersDto2.setFirstName("updatedFirstName");
        usersDto2.setLastName("updatedLastName");

        when(userRepo.findById(1)).thenReturn(Optional.of(user1));
        when(userRepo.save(user1)).thenReturn(user1);
        when(userMapper.mapToDto(user1)).thenReturn(usersDto2);

        UsersDto actualDto = userService.updateUser(usersDto1, 1);

        assertNotNull(actualDto);
        assertEquals(usersDto2.getUserId(), actualDto.getUserId());
        assertEquals(usersDto2.getFirstName(), actualDto.getFirstName());
        assertEquals(usersDto2.getLastName(), actualDto.getLastName());

        verify(userRepo).findById(1);
        verify(userMapper).updateUser(usersDto1, user1);
        verify(userRepo).save(user1);
        verify(userMapper).mapToDto(user1);


    }

    @Test
    void getUserByIdShouldThrowUserNotFoundException() {
        when(userRepo.findById(8)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            userService.getUserById(8);
        });
        assertEquals("User not found with ID: 8", exception.getMessage());
    }

    @Test
    void addRolesShouldThrowExceptionforInvalidName() {
        when(userRepo.findById(1)).thenReturn(Optional.of(user1));//mock find by id
        RolesEntity role = new RolesEntity();
        Set<String> names = new HashSet<>();
        names.add("ma");

        InvalidRoleException exception = assertThrows(InvalidRoleException.class, () -> {
            userService.addRoles(names, user1.getUserId());
        });

        assertEquals("ma is not a valid role", exception.getMessage());
    }
}