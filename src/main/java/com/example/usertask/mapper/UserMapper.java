package com.example.usertask.mapper;
import com.example.usertask.dto.UserDto;
import com.example.usertask.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


    @Mapper(componentModel = "spring")
    public interface UserMapper {
        @Mapping(target = "fullName",expression = "java(source.getFirstName() + \" \" + source.getLastName())")
        UserDto mapToDto(User source);

        User mapToUser(UserDto source);


    }

