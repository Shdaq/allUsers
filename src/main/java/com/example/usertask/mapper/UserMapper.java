package com.example.usertask.mapper;
import com.example.usertask.dto.UsersDto;
import com.example.usertask.entity.RolesEntity;
import com.example.usertask.entity.UsersEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.*;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
    public interface UserMapper {
        @Mapping(target = "fullName",expression = "java(source.getFirstName() + \" \" + source.getLastName())")
        @Mapping(target = "roles", expression = "java(mapRoles(source.getRoles()))")
        UsersDto mapToDto(UsersEntity source);

        @Mapping(target = "roles", ignore = true)
        UsersEntity mapToUser(UsersDto source);

        @Mapping(target = "userId", ignore = true)
        @Mapping(target = "roles", ignore = true)
        void updateUser(UsersDto userDto, @MappingTarget UsersEntity matchedUser);

        default Set<String> mapRoles(Set<RolesEntity> roles){
            if (roles==null)
                return null;
            return roles.stream()
                    .map(RolesEntity::getRole)
                    .collect(Collectors.toSet());
        }


    }

