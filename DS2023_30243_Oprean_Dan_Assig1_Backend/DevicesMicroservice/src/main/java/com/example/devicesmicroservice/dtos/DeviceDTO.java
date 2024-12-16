package com.example.devicesmicroservice.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;
import java.util.UUID;

public class DeviceDTO extends RepresentationModel<DeviceDTO> {
    private String deviceId;
    private String description;
    private String address;
    private int consumption;
    private String userId;

    public DeviceDTO() {
    }

    public DeviceDTO(String id, String description, String address, int consumption, String userId) {
        this.deviceId = id;
        this.description = description;
        this.address = address;
        this.consumption = consumption;
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return deviceId;
    }

    public void setId(String id) {
        this.deviceId = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceDTO deviceDTO = (DeviceDTO) o;
        return description == deviceDTO.description &&
                Objects.equals(address, deviceDTO.address) &&
                Objects.equals(consumption, deviceDTO.consumption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, address, consumption);
    }
}

