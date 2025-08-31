package com.example.userTask.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.userTask.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

}