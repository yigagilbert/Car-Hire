package com.wip.carrental.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.wip.carrental.controller.exceptions.ResourceNotFoundException;
import com.wip.carrental.model.*;
import com.wip.carrental.repository.ReservationRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wip.carrental.repository.ParkingLocationRepository;
import com.wip.carrental.repository.VehicleRepository;


@RestController
@RequestMapping("/api")
@Api(value = "Vehicle Management System")
@CrossOrigin(origins = "http://localhost:3000")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ParkingLocationRepository parkingLocationRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping("/vehicles")
    public ArrayList<Vehicle> getAllVehicles() {
        return (ArrayList<Vehicle>) vehicleRepository.findAll();
    }

//    @GetMapping("/vehicles/{locationId}")
//    public ArrayList<Vehicle> getVehiclesByLocation(@PathVariable long locationId) {
//        ParkingLocation parkingLocation =  parkingLocationRepository.findById(locationId).orElse(null);
//        return vehicleRepository.findAllByParkingLocation(parkingLocation);
//    }

    @GetMapping("/vehicles/{vehicleId}")
    public Optional<Vehicle> getVehicleById(@PathVariable Long vehicleId) {
        return vehicleRepository.findById(vehicleId);
    }

    @GetMapping("/vehicles/{vehicleId}/reviews")
    public ArrayList<Review> getAllReviewsForVehicle(@PathVariable Long vehicleId) {
        ArrayList<Reservation> reservations = (ArrayList<Reservation>) reservationRepository.findAll();
        ArrayList<Review> reviews = new ArrayList<>();
        for (Reservation r : reservations) {
            if (r.getVehicle().getVehicleId() == vehicleId) {
                reviews.add(r.getReview());
            }
        }

        return reviews;
    }

    @GetMapping("/vehicles/{vehicleId}/rating")
    public float getAverageRatingForVehicle(@PathVariable Long vehicleId) {
        ArrayList<Reservation> reservations = (ArrayList<Reservation>) reservationRepository.findAll();
        int totalCount = 0;
        float rating = 0;
        for (Reservation r : reservations) {
            if (r.getVehicle().getVehicleId() == vehicleId) {
                rating += r.getReview().getRating();
                totalCount++;
            }
        }

        if (totalCount < 2) {
            return rating;
        }

        return rating / totalCount;
    }


    //Need to track parking location capacity here when adding a vehicle
    @PostMapping("/vehicles/{locationid}")
    public ResponseEntity<?> postVehicle(@RequestBody Vehicle vehicle, @PathVariable Long locationid) {

        ParkingLocation parkingLocation = parkingLocationRepository.findById(locationid).orElse(null);

        if (parkingLocation != null) {
            if (parkingLocation.getCapacity() > parkingLocation.getFilledSpots()) {
                vehicle.setParkingLocation(parkingLocation);
                parkingLocation.setFilledSpots(parkingLocation.getFilledSpots() + 1);
                return ResponseEntity.ok(vehicleRepository.save(vehicle));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parking location is full");
            }

        } else {
            throw new ResourceNotFoundException("Parking Location with ID " + locationid + " not found");
        }

    }

    @PostMapping("/vehicles/search")
    public ResponseEntity<?> SearchVehicles(@RequestBody SearchRequestBody location) {
        try {
            ArrayList<ParkingLocation> parkingLocations = (ArrayList<ParkingLocation>) parkingLocationRepository.findAll();

            String requestCity = location.getCity() == null ? "" : location.getCity();
            String type = location.getType() == null ? "" : location.getType();
            List<ParkingLocation> cityLocations = parkingLocations.stream().filter(s -> s.getCity().toLowerCase().contains(requestCity.toLowerCase())).collect(Collectors.toList());

            ArrayList<Vehicle> result = new ArrayList<>();

            if (type.equalsIgnoreCase("sedan") || type.equalsIgnoreCase("truck") || type.equalsIgnoreCase("suv") || type.equalsIgnoreCase("small")) {
                for (ParkingLocation city : cityLocations) {
                    ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) vehicleRepository.findAllByParkingLocation(city);

                    if (vehicles != null) {
                        result.addAll(Objects.requireNonNull(vehicles.stream().filter(s -> s.getVehicleType().toString().equalsIgnoreCase(type)).collect(Collectors.toList())));
                    }
                }

                if (result.size() == 0) {
                    for (ParkingLocation city : parkingLocations) {
                        ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) vehicleRepository.findAllByParkingLocation(city);

                        if (vehicles != null) {
                            result.addAll(Objects.requireNonNull(vehicles.stream().filter(s -> s.getVehicleType().toString().equalsIgnoreCase(type)).collect(Collectors.toList())));
                        }
                    }
                }
            } else {
                for (ParkingLocation city : cityLocations) {
                    ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) vehicleRepository.findAllByParkingLocation(city);

                    if (vehicles != null) {
                        result.addAll(Objects.requireNonNull(vehicles));
                    }
                }
            }

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage());

        }
    }

    @PutMapping("/vehicles/{vehicleId}")
    public ResponseEntity<?> updateVehicle(@PathVariable Long vehicleId, @RequestBody Vehicle vehicleRequestBody) {
        return vehicleRepository.findById(vehicleId).map(vehicle -> {
            vehicle.setVehicleName(vehicleRequestBody.getVehicleName());
            vehicle.setDescription(vehicleRequestBody.getDescription());
            vehicle.setVehicleBasePrice(vehicleRequestBody.getVehicleBasePrice());
            vehicle.setVehicleType(vehicleRequestBody.getVehicleType());
            vehicle.setParkingLocation(vehicleRequestBody.getParkingLocation());
            vehicle.setStatus(vehicleRequestBody.getStatus());
            return ResponseEntity.ok(vehicleRepository.save(vehicle));
        }).orElseThrow(() -> new ResourceNotFoundException("Vehicle ID " + vehicleId + " not found"));
    }


    @DeleteMapping("vehicles/{vehicleId}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long vehicleId) {
        if (vehicleRepository.existsById(vehicleId)) {
            Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
            ParkingLocation parkingLocation = vehicle.getParkingLocation();
            parkingLocation.setFilledSpots(parkingLocation.getFilledSpots() - 1);
            vehicleRepository.deleteById(vehicleId);

            return ResponseEntity.ok("Vehicle with " + vehicleId + " deleted");
        }
        throw new ResourceNotFoundException("Vehicle with " + vehicleId + " not found");

    }
}
