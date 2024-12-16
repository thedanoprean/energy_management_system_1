package com.example.energymanagement.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.energymanagement.controllers.handlers.exceptionsmodel.ResourceNotFoundException;
import com.example.energymanagement.dtos.UserDTO;
import com.example.energymanagement.dtos.UserDetailsDTO;
import com.example.energymanagement.dtos.builders.UserBuilder;
import com.example.energymanagement.entities.Users;
import com.example.energymanagement.repositories.UserRepository;
import org.springframework.web.client.RestTemplate;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public UserService(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }


    private void validateUserDetails(UserDetailsDTO userDTO) {
        if (userDTO.getUsername() == null || userDTO.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Numele de utilizator nu poate fi gol.");
        }

        if (userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Parola nu poate fi goală.");
        }

        if (!userDTO.getRole().equals("user") && !userDTO.getRole().equals("admin")) {
            throw new IllegalArgumentException("Rolul utilizatorului trebuie să fie 'user' sau 'admin'.");
        }

    }

    public String saveUser(UserDetailsDTO userDTO) {
        validateUserDetails(userDTO);

        Users user = UserBuilder.toEntity(userDTO);
        String randomUserId = UUID.randomUUID().toString();
        user.setId(randomUserId);
        userRepository.save(user);

        // Adăugare id-ul utilizatorului în tabela "UserID" a microserviciului de Device
        ResponseEntity<String> responseEntityDevice = restTemplate.postForEntity("http://container-device-microservice:8080/device/user2/" + user.getId(), null, String.class);

        LOGGER.debug("User with id {} was inserted in db", user.getId());
        return user.getId();
    }

    public Users getUser(String userId) {
        //get user from database with the help of user repository
        Users user =  userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found" + userId));
        return user;
    }

    public List<Users> getAllUser() {

        List<Users> users = userRepository.findAll();
        return users;
    }

    public void updateUser(String userId, UserDetailsDTO updatedUserDTO) {
        Users existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(Users.class.getSimpleName() + " with id: " + userId));

        // Update the existing user with data from updatedUserDTO
        existingUser.setName(updatedUserDTO.getName());
        existingUser.setPassword(updatedUserDTO.getPassword());
        existingUser.setRole(updatedUserDTO.getRole());
        existingUser.setUsername(updatedUserDTO.getUsername());

        userRepository.save(existingUser);
        LOGGER.debug("User with id {} was updated in db", userId);
    }

    public void deleteUser(String userId) {
        if (userRepository.existsById(userId)) {
            // Șterge id-ul utilizatorului din tabela "UserID" a microserviciului de Device
            restTemplate.exchange("http://container-device-microservice:8080/device/user2/delete/" + userId, HttpMethod.DELETE, null, String.class);

            userRepository.deleteById(userId);
            LOGGER.debug("User with id {} was deleted from db", userId);
        } else {
            LOGGER.error("User with id {} was not found in db", userId);
            throw new ResourceNotFoundException(Users.class.getSimpleName() + " with id: " + userId);
        }
    }

    public String authenticateUser(String username, String password) throws AuthenticationException {
        Users user = userRepository.findByUsernameAndPassword(username, password);
        if (user == null) {
            throw new AuthenticationException("Invalid username or password");
        }
        return user.getId();
    }
}
