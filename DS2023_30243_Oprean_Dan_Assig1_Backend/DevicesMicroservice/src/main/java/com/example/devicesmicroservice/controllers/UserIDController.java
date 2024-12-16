package com.example.devicesmicroservice.controllers;

import com.example.devicesmicroservice.entities.Device;
import com.example.devicesmicroservice.entities.UserID;
import com.example.devicesmicroservice.services.UserIDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/device/user2")
public class UserIDController {
    @Autowired
    private UserIDService userIDService;

    @PostMapping("/{userid}")
    public ResponseEntity create(@PathVariable String userid){
        UserID userid1 = userIDService.create(userid);
        return ResponseEntity.status(HttpStatus.CREATED).body(userid1);
    }

    @GetMapping
    public ResponseEntity<List<UserID>> getUserIds(){
        return ResponseEntity.ok(userIDService.getAllUserIds());

    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Device> deleteUserId(@PathVariable String userId) throws IOException {
        userIDService.delete(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
