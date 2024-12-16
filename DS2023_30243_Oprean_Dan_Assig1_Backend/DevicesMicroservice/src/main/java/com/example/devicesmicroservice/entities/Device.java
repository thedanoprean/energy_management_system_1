package com.example.devicesmicroservice.entities;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Device implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="deviceId")
    private String deviceId;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "consumption", nullable = false)
    private int consumption;

    @ManyToOne()
    @JoinColumn(name = "userId")
    private UserID userID;

    public Device() {
    }

    public Device(String description, String address, int consumption, UserID userID) {
        this.description = description;
        this.address = address;
        this.consumption = consumption;
        this.userID = userID;
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

    public UserID getUserID() {
        return userID;
    }

    public void setUserID(UserID userID) {
        this.userID = userID;
    }

}
