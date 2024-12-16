package com.example.energymanagement.controllers;

import com.example.energymanagement.controllers.handlers.exceptionsmodel.ResourceNotFoundException;
import com.example.energymanagement.login.LoginRequest;
import com.example.energymanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.energymanagement.entities.Users;
import com.example.energymanagement.dtos.UserDTO;
import com.example.energymanagement.dtos.UserDetailsDTO;
import com.example.energymanagement.services.UserService;
import org.springframework.web.client.RestTemplate;


import javax.naming.AuthenticationException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Users> getSingleUser(@PathVariable String userId) {
        Users user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/")
    public ResponseEntity<List<Users>> getAllUser() {
        List<Users> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }

    @PostMapping("/")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDetailsDTO userDTO) {
        try {
            if (!userDTO.getRole().equals("user") && !userDTO.getRole().equals("admin")) {
                throw new IllegalArgumentException("Rolul utilizatorului trebuie să fie 'user' sau 'admin'.");
            }
            String userId = userService.saveUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(userId);
        } catch (IllegalArgumentException e) {
            // Tratați excepția și returnați un răspuns de eroare cu codul de stare 400 BAD REQUEST
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
        }
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") String userId, @Valid @RequestBody UserDetailsDTO updatedUserDTO) {
        userService.updateUser(userId, updatedUserDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> userLogin(@RequestBody LoginRequest userLoginRequest) {
        try {
            String username = userLoginRequest.getUsername();
            String password = userLoginRequest.getPassword();

            if (username.equals("admin") && password.equals("admin123")) {
                // Autentificare cu rol de admin
                Map<String, String> response = new HashMap<>();
                response.put("userId", "admin"); // Utilizatorul admin are ID-ul "admin"
                response.put("role", "admin"); // Adaugă rolul pentru frontend

                return ResponseEntity.ok(response);
            } else {
                // Verificare în baza de date pentru utilizatori obișnuiți
                String userId = userService.authenticateUser(username, password);
                Map<String, String> response = new HashMap<>();
                response.put("userId", userId);
                response.put("role", "user"); // Adaugă rolul pentru frontend

                return ResponseEntity.ok(response);
            }
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
