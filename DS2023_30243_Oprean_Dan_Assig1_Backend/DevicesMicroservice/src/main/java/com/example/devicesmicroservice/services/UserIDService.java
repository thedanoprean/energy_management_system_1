package com.example.devicesmicroservice.services;

import com.example.devicesmicroservice.controllers.handlers.exceptionsmodel.ResourceNotFoundException;
import com.example.devicesmicroservice.entities.Device;
import com.example.devicesmicroservice.entities.UserID;
import com.example.devicesmicroservice.repositories.DeviceRepository;
import com.example.devicesmicroservice.repositories.UserIDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
public class UserIDService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private UserIDRepository repository;

    public UserID create(String userid) {
        return repository.save(new UserID(userid));
    }


    public List<UserID> getAllUserIds() {
        return repository.findAll();
    }

    public void delete(String userid) throws IOException {
        UserID userId = repository.findByUserId(userid);
        if (userId != null) {
            List<Device> devices =  deviceRepository.findByUserID(userId);
            deviceRepository.deleteAll(devices);
            repository.delete(userId);
        } else {
            throw new ResourceNotFoundException(Device.class.getSimpleName() + " with id: " + userId);
        }
    }

}
