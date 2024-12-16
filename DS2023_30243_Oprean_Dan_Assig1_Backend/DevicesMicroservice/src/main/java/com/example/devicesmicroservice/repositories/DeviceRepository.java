package com.example.devicesmicroservice.repositories;

import com.example.devicesmicroservice.entities.UserID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.devicesmicroservice.entities.Device;

import java.util.List;
import java.util.Optional;


public interface DeviceRepository extends JpaRepository<Device, String> {

    List<Device> findByUserID(UserID userId);
    List<Device> findByUserID_userId(String userId);

}
