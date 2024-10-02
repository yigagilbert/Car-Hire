package com.wip.carrental.controller;

import com.wip.carrental.controller.exceptions.ResourceNotFoundException;
import com.wip.carrental.repository.DriverRepository;
import com.wip.carrental.repository.ReservationRepository;
import com.wip.carrental.model.Driver;
import com.wip.carrental.model.Reservation;

import io.swagger.annotations.Api;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "Driver Management System")
public class DriverController {
    @Autowired
    private DriverRepository driverRepository;
    
    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping("/drivers")
    public List<Driver> getAllDrivers() {
        return (List<Driver>) driverRepository.findAll();
    }

    @GetMapping("/drivers/{id}")
    public Optional<Driver> getDriverById(@PathVariable String id) {
        return driverRepository.findById(id);
    }
    
    @GetMapping("/driver/reservations")
    public List<Reservation> getReservations(@RequestBody Driver driver) {
    	return driver.getReservations();
    }
    

    @PostMapping("/drivers")
    public ResponseEntity<?> postDriver(@RequestBody Driver driverObj) {

        try {
            driverObj.setDriverPassword(hashPassword(driverObj.getDriverPassword()));
            driverObj.setdMembership();

            return ResponseEntity.ok(driverRepository.save(driverObj));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    private String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    @PostMapping("/drivers/login")
    public ResponseEntity<?> loginDriver(@RequestBody Driver requestObj) {
        Driver driver = driverRepository.findById(requestObj.getDriverEmailId()).orElse(null);
        if (driver != null) {
            if (checkPass(requestObj.getDriverPassword(),driver.getDriverPassword())) {
                return ResponseEntity.ok(driver);
            }
            return ResponseEntity.status(403).body("password is not matching");
        }
        return ResponseEntity.status(400).body("driver not found");
    }

    private boolean checkPass(String plainPassword, String hashedPassword) {
        return (BCrypt.checkpw(plainPassword, hashedPassword));
    }

    
    @PutMapping("/drivers/{driverEmailId}")
    public ResponseEntity<?> updateDriver(@PathVariable String driverEmailId, @RequestBody Driver driverRequestBody) {
        return driverRepository.findById(driverEmailId).map(driver -> {
            driver.setDriverAddress(driverRequestBody.getDriverAddress());
            driver.setDriverName(driverRequestBody.getDriverName());
            driver.setDriverPassword(hashPassword(driverRequestBody.getDriverPassword()));
            return ResponseEntity.ok(driverRepository.save(driver));
        }).orElseThrow(() -> new ResourceNotFoundException("Email ID " + driverEmailId + " not found"));
    }
    
    //Update Membership fee
    @PutMapping("/drivers/{driverEmailId}/updateFee")
    public ResponseEntity<?> changeMemberShipFee(@PathVariable String driverEmailId, @RequestParam(value = "memberShipFee") Double newFee) {
        Driver driver = driverRepository.findById(driverEmailId).orElse(null);
        if (driver != null) {
        	driver.setMemberShipFee(newFee);
        	return ResponseEntity.ok(driverRepository.save(driver));
        } else {
        	return ResponseEntity.notFound().eTag("driver not found").build();
        }
    }
    
    //End membership for driver
    @PutMapping("/drivers/{driverEmailId}/endMembership" )
    public ResponseEntity<?> endMemberShip(@PathVariable String driverEmailId) {
    	 Driver driver = driverRepository.findById(driverEmailId).orElse(null);
         if (driver != null) {
         	driver.setMember(false);
         	return ResponseEntity.ok(driverRepository.save(driver));
         } else {
         	return ResponseEntity.notFound().eTag("driver not found").build();
         }
    }

    @DeleteMapping("/drivers/{driverEmailId}")
    public ResponseEntity<?> deleteDriver(@PathVariable String driverEmailId) {
        return driverRepository.findById(driverEmailId).map(driver -> {
            driverRepository.delete(driver);
            return ResponseEntity.ok("driverEmailId " + driverEmailId + " deleted");
        }).orElseThrow(() -> new ResourceNotFoundException("driverEmailId " + driverEmailId + " not found"));
    }
}
