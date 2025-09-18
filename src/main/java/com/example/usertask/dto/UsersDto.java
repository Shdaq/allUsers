package com.example.usertask.dto;

import java.util.*;
import com.example.usertask.entity.RolesEntity;
import lombok.*;

@Data
@NoArgsConstructor
public class UsersDto {

    private Integer userId;

    private String firstName;

    private String lastName;

    private String fullName;

    private Date dob;

    private Set<String> roles;


}

