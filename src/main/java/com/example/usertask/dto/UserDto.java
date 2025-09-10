package com.example.usertask.dto;
import com.example.usertask.model.User;
import java.util.Date;
import lombok.*;

@Data
@NoArgsConstructor
public class UserDto {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String fullName;
    private Date dob;


}

