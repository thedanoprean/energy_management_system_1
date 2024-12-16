package com.example.energymanagement.dtos.builders;

import com.example.energymanagement.dtos.UserDTO;
import com.example.energymanagement.dtos.UserDetailsDTO;
import com.example.energymanagement.entities.Users;

public class UserBuilder {

    private UserBuilder() {
    }

    public static UserDTO toUserDTO(Users user) {
        return new UserDTO(user.getId(), user.getName(), user.getUsername(), user.getPassword(), user.getRole());
    }

    public static UserDetailsDTO toUserDetailsDTO(Users user) {
        return new UserDetailsDTO(user.getId(), user.getName(), user.getUsername(), user.getPassword(), user.getRole());
    }

    public static Users toEntity(UserDetailsDTO userDetailsDTO) {
        return new Users(userDetailsDTO.getName(),
                userDetailsDTO.getUsername(),
                userDetailsDTO.getPassword(),
                userDetailsDTO.getRole());
    }
}
