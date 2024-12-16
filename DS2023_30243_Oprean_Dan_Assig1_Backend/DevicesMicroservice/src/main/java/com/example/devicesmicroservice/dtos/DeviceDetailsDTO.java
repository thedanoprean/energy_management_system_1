package com.example.devicesmicroservice.dtos;


import com.example.devicesmicroservice.entities.UserID;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

public class DeviceDetailsDTO {

    private String deviceId;
    @NotNull
    private String description;
    @NotNull
    private String address;
    @NotNull
    private int consumption;
    private String userId;

    public DeviceDetailsDTO() {
    }

    public DeviceDetailsDTO( String description, String address, int consumption) {
        this.description = description;
        this.address = address;
        this.consumption = consumption;
    }

    public DeviceDetailsDTO(String id, String description, String address, int consumption ) {
        this.deviceId = id;
        this.description = description;
        this.address = address;
        this.consumption = consumption;
    }

    public DeviceDetailsDTO(String deviceId, String description, String address, int consumption, String userId) {
        this.deviceId = deviceId;
        this.description = description;
        this.address = address;
        this.consumption = consumption;
        this.userId = userId;
    }

    public String getId() {
        return deviceId;
    }

    public DeviceDetailsDTO(String description, String address, int consumption, String userId) {
        this.description = description;
        this.address = address;
        this.consumption = consumption;
        this.userId = userId;
    }

    public String getUserid() {
        return userId;
    }

    public void setUserid(String userId) {
        this.userId = userId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceDetailsDTO that = (DeviceDetailsDTO) o;
        return description == that.description &&
                Objects.equals(address, that.address) &&
                Objects.equals(consumption, that.consumption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, address, consumption);
    }
}

