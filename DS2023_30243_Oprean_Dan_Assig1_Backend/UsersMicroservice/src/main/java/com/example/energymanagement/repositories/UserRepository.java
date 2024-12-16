package com.example.energymanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.energymanagement.entities.Users;


public interface UserRepository  extends JpaRepository<Users, String> {

    Users findByUsername(String username);

    Users findByUsernameAndPassword(String username, String password);

    boolean existsByUsername(String username);
}

