
package com.example.devicesmicroservice.repositories;

import com.example.devicesmicroservice.entities.UserID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserIDRepository extends JpaRepository<UserID, String> {
    UserID findByUserId(String userid);
}