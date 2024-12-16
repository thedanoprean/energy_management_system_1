// DeviceMicroservice/entities/UserID.java
package com.example.devicesmicroservice.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class UserID implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "userId")
    private String userId;

    public UserID() {
    }

    public UserID(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return userId;
    }

    public void setId(String id) {
        this.userId = id;
    }

    public String getUser_id() {
        return userId;
    }

    public void setUser_id(String userId) {
        this.userId = userId;
    }


}
