package com.example.devicesmicroservice.dtos.builders;

import com.example.devicesmicroservice.dtos.DeviceDTO;
import com.example.devicesmicroservice.dtos.DeviceDetailsDTO;
import com.example.devicesmicroservice.entities.Device;
import com.example.devicesmicroservice.entities.UserID;

public class DeviceBuilder {

    private DeviceBuilder() {
    }

    public static DeviceDTO toDeviceDTO(Device device) {
        return new DeviceDTO(device.getId(), device.getDescription(), device.getAddress(), device.getConsumption(), device.getUserID().getId());
    }

    public static DeviceDetailsDTO toDeviceDetailsDTO(Device device) {
        return new DeviceDetailsDTO(device.getId(), device.getDescription(), device.getAddress(), device.getConsumption(), device.getUserID().getUser_id());
    }

    public static Device toEntity(DeviceDetailsDTO deviceDetailsDTO) {
        return new Device(deviceDetailsDTO.getDescription(),
                deviceDetailsDTO.getAddress(),
                deviceDetailsDTO.getConsumption(),
                new UserID(deviceDetailsDTO.getUserid()));
    }
}
