package com.example.usertask.repository;

import com.example.usertask.entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface RolesRepo extends JpaRepository<RolesEntity,Integer> {
    Optional<RolesEntity> findByRole(String role);

}
