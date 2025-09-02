package com.example.usertask.dto;
import com.example.usertask.model.User;
import java.util.Date;
import lombok.*;


public class UserDto {
    private int userId;
    private String firstName;
    private String lastName;
    private String fullName;
    private Date dob;


    public UserDto(User user) {
        this.userId = user.getUserId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.dob = user.getDob();
        this.fullName = user.getFirstName() + " " + user.getLastName();
    }

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public Date getDob() {
        return dob;
    }
}

