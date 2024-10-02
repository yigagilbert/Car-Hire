package com.wip.carrental.controller;

import com.wip.carrental.controller.exceptions.ResourceNotFoundException;
import com.wip.carrental.model.ParkingLocation;
import com.wip.carrental.repository.ParkingLocationRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "Parking Management System")
public class ParkingLocationController {
    @Autowired
    private ParkingLocationRepository parkingLocationRepository;

    @GetMapping("/locations")
    public List<ParkingLocation> getAllParkingLocations() {
        return (List<ParkingLocation>) parkingLocationRepository.findAll();
    }

    @GetMapping("/locations/{id}")
    public Optional<ParkingLocation> getParkingLocationById(@PathVariable Long id) {
        return parkingLocationRepository.findById(id);
    }

    @PostMapping("/locations")
    public ResponseEntity<?> postParkingLocation(@RequestBody ParkingLocation parkingLocation) {
        return ResponseEntity.ok(parkingLocationRepository.save(parkingLocation));
    }



    @PutMapping("/locations/{locationId}")
    public ResponseEntity<?> updateParkingLocation(@PathVariable Long locationId, @RequestBody ParkingLocation parkingLocation) {
        return parkingLocationRepository.findById(locationId).map(location -> {
            location.setAddress(parkingLocation.getAddress());
            location.setCity(parkingLocation.getCity());
            location.setCapacity(parkingLocation.getCapacity());
            return ResponseEntity.ok(parkingLocationRepository.save(location));
        }).orElseThrow(() -> new ResourceNotFoundException("location with id = " + locationId + " not found"));
    }

    @DeleteMapping("/locations/{locationId}")
    public ResponseEntity<?> deleteParkingLocation(@PathVariable Long locationId) {
        return parkingLocationRepository.findById(locationId).map(location -> {
            parkingLocationRepository.delete(location);
            return ResponseEntity.ok("ParkingLocation with id = " + locationId + " deleted");
        }).orElseThrow(() -> new ResourceNotFoundException("ParkingLocation with id = " + locationId + " not found"));
    }
}
