package com.example.devicesmicroservice.services;

import com.example.devicesmicroservice.entities.UserID;
import com.example.devicesmicroservice.repositories.UserIDRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.devicesmicroservice.controllers.handlers.exceptionsmodel.ResourceNotFoundException;
import com.example.devicesmicroservice.dtos.DeviceDTO;
import com.example.devicesmicroservice.dtos.DeviceDetailsDTO;
import com.example.devicesmicroservice.dtos.builders.DeviceBuilder;
import com.example.devicesmicroservice.entities.Device;
import com.example.devicesmicroservice.repositories.DeviceRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeviceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceService.class);
    private final DeviceRepository deviceRepository;
    private final UserIDRepository userIdRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository, UserIDRepository userIdRepository) {
        this.deviceRepository = deviceRepository;
        this.userIdRepository = userIdRepository;
    }

    public List<DeviceDTO> findDevices() {
        List<Device> deviceList = deviceRepository.findAll();
        return deviceList.stream()
                .map(DeviceBuilder::toDeviceDTO)
                .collect(Collectors.toList());
    }

    public DeviceDetailsDTO findDeviceById(String id) {
        Optional<Device> prosumerOptional = deviceRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Device with id {} was not found in db", id);
            throw new ResourceNotFoundException(Device.class.getSimpleName() + " with id: " + id);
        }
        return DeviceBuilder.toDeviceDetailsDTO(prosumerOptional.get());
    }

    public String insertDevice(DeviceDetailsDTO deviceDTO, String userId) {
        Optional<UserID> userOptional = userIdRepository.findById(userId);
        if (userOptional.isPresent()) {
            UserID user = userOptional.get();
            deviceDTO.setUserid(userId); // Setează userId în DTO
            Device device = DeviceBuilder.toEntity(deviceDTO);
            device.setUserID(user);
            String randomDeviceId = UUID.randomUUID().toString();
            device.setId(randomDeviceId);
            device = deviceRepository.save(device);
            LOGGER.debug("Device with id {} was inserted in db for user with id {}", device.getId(), userId);
            return device.getId();
        } else {
            LOGGER.error("User with id {} was not found in db", userId);
            throw new ResourceNotFoundException(UserID.class.getSimpleName() + " with id: " + userId);
        }
    }

    public void updateDevice(String deviceId, DeviceDetailsDTO updatedDeviceDTO) {
        Optional<Device> deviceOptional = deviceRepository.findById(deviceId);
        Optional<UserID> userOptional = userIdRepository.findById(updatedDeviceDTO.getUserid());

        if (deviceOptional.isPresent() && userOptional.isPresent()) {
            Device existingDevice = deviceOptional.get();
            existingDevice.setAddress(updatedDeviceDTO.getAddress());
            existingDevice.setConsumption(updatedDeviceDTO.getConsumption());
            existingDevice.setDescription(updatedDeviceDTO.getDescription());
            existingDevice.setUserID(userOptional.get());
            deviceRepository.save(existingDevice);
            LOGGER.debug("Device with id {} was updated in db", deviceId);
        } else {
            LOGGER.debug("Received deviceId: {}", deviceId);
            LOGGER.debug("Received userId: {}", updatedDeviceDTO.getUserid());
            LOGGER.error("Device or User with provided id was not found in db");
            throw new ResourceNotFoundException("Device or User with provided id not found");
        }
    }


    public void deleteDevice(String deviceId) {
        Optional<Device> deviceOptional = deviceRepository.findById(deviceId);
        if (deviceOptional.isPresent()) {
            deviceRepository.deleteById(deviceId);
            LOGGER.debug("Device with id {} was deleted from db", deviceId);
        } else {
            LOGGER.error("Device with id {} was not found in db", deviceId);
            throw new ResourceNotFoundException(Device.class.getSimpleName() + " with id: " + deviceId);
        }
    }

    public List<DeviceDTO> findDevicesByUserId(String userId) {
        List<Device> deviceList = deviceRepository.findByUserID_userId(userId);
        return deviceList.stream()
                .map(DeviceBuilder::toDeviceDTO)
                .collect(Collectors.toList());
    }

}
