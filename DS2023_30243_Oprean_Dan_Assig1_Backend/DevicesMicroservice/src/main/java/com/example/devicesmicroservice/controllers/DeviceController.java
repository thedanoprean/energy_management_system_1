package com.example.devicesmicroservice.controllers;

import com.example.devicesmicroservice.repositories.UserIDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.devicesmicroservice.dtos.DeviceDTO;
import com.example.devicesmicroservice.dtos.DeviceDetailsDTO;
import com.example.devicesmicroservice.services.DeviceService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@CrossOrigin(origins = "http://localhost:3003")
@RequestMapping(value = "/device")
public class DeviceController {

    private final DeviceService deviceService;
    private final UserIDRepository userIDRepository;

    @Autowired
    public DeviceController(DeviceService deviceService, UserIDRepository userIDRepository) {
        this.deviceService = deviceService;
        this.userIDRepository = userIDRepository;
    }

    @GetMapping()
    public ResponseEntity<List<DeviceDTO>> getDevices() {
        List<DeviceDTO> dtos = deviceService.findDevices();
        for (DeviceDTO dto : dtos) {
            Link userLink = linkTo(methodOn(DeviceController.class)
                    .getDevice(dto.getId())).withRel("deviceDetails");
            dto.add(userLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> insertProsumer(@Valid @RequestBody DeviceDetailsDTO deviceDTO) {
        String userId = deviceDTO.getUserid(); // Obține userId-ul din DTO
        String deviceID = deviceService.insertDevice(deviceDTO, userId);
        return new ResponseEntity<>(deviceID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DeviceDetailsDTO> getDevice(@PathVariable("id") String deviceId) {
        DeviceDetailsDTO dto = deviceService.findDeviceById(deviceId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateDevice(@PathVariable("id") String deviceId, @Valid @RequestBody DeviceDetailsDTO updatedDeviceDTO) {
        deviceService.updateDevice(deviceId, updatedDeviceDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable("id") String deviceId) {
        deviceService.deleteDevice(deviceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DeviceDTO>> getDevicesByUserId(@PathVariable("userId") String userId) {
        List<DeviceDTO> dtos = deviceService.findDevicesByUserId(userId);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
