package com.example.usertask.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.usertask.entity.UsersEntity;

@Repository
public interface UsersRepo extends JpaRepository<UsersEntity, Integer> {

}