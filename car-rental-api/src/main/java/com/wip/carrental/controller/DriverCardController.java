package com.wip.carrental.controller;


import com.wip.carrental.controller.exceptions.ResourceNotFoundException;
import com.wip.carrental.model.Driver;
import com.wip.carrental.repository.DriverCardRepository;
import com.wip.carrental.repository.DriverRepository;
import com.wip.carrental.model.DriverCard;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "Drivercard Management System")
public class DriverCardController {
    @Autowired
    private DriverCardRepository driverCardRepository;

    @Autowired
    private DriverRepository driverRepository;

    @GetMapping("/drivers/{driverEmailId}/cards")
    public Optional<List<DriverCard>> getDriverCardsByLicense(@PathVariable String driverEmailId) {
        return driverRepository.findById(driverEmailId).map(driver ->
                driverCardRepository.findDriverCardsByDriver(driver)
        );
    }

    @PostMapping("/drivers/{driverEmailId}/cards")
    public DriverCard postDriverCard(@PathVariable String driverEmailId, @Valid @RequestBody DriverCard driverCard) {
        return driverRepository.findById(driverEmailId).map(driver -> {
            driverCard.setDriver(driver);
            return driverCardRepository.save(driverCard);
        }).orElseThrow(() -> new ResourceNotFoundException("driverEmailId " + driverEmailId + " not found"));
    }

    @DeleteMapping("/drivers/{driverEmailId}/cards/{cardNumber}")
    public ResponseEntity<?> deleteDriverCard(@PathVariable String driverEmailId, @PathVariable Long cardNumber) {

        Driver driver = driverRepository.findById(driverEmailId).orElse(null);
        if (driver != null) {
            DriverCard card = driverCardRepository.findByDriverAndDriverCardNumber(driver, cardNumber).orElse(null);
            if (card != null) {
                driverCardRepository.delete(card);
                return ResponseEntity.ok("driverEmailId " + driverEmailId + "cardNumber " + cardNumber + " deleted");
            }
        }
        throw new ResourceNotFoundException("driverEmailId " + driverEmailId + "cardNumber " + cardNumber + " not found");
    }
}
