package com.wip.carrental.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wip.carrental.controller.exceptions.ResourceNotFoundException;
import com.wip.carrental.model.Driver;
import com.wip.carrental.model.ParkingLocation;
import com.wip.carrental.model.Reservation;
import com.wip.carrental.model.ReservationStatus;
import com.wip.carrental.model.Vehicle;
import com.wip.carrental.model.VehicleStatus;
import com.wip.carrental.repository.DriverRepository;
import com.wip.carrental.repository.ReservationRepository;
import com.wip.carrental.repository.VehicleRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "Reservation Management System")
public class ReservationController {
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
	private VehicleRepository vehicleRepository;
	 
	@GetMapping("/reservations")
    public List<Reservation> getAllReservations() {
        return (List<Reservation>) reservationRepository.findAll();
    }

    @GetMapping("/reservations/{reservationId}")
    public Optional<Reservation> getReservationById(@PathVariable Long reservationId) {
        return reservationRepository.findById(reservationId);
    }
    
    

    @PostMapping("/reservation")
    public ResponseEntity<?> postReservation(@RequestBody Reservation reservation, @RequestParam(value = "driverEmailId", required = false) String driverEmailId, 
    											@RequestParam(value = "vehicle_id", required = false) Long vehicle_id) {
    	//Driver driver = reservation.getDriver();

    	Driver driver = driverRepository.findById(driverEmailId).orElse(null);
    	Vehicle vehicle = vehicleRepository.findById(vehicle_id).orElse(null);
    	
    	//Check for valid driverEmailId and vehicle_id
    	if( driver == null && vehicle == null) {
			ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Driver with ID " + driverEmailId + " not found "
    	                                         + "Vehicle with ID " + vehicle_id + " not found"); 
    	} else if (driver == null ) {
			ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Driver with ID " + driverEmailId + " not found ");
    	} else if (vehicle == null ) {
			ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vehicle with ID " + vehicle_id + " not found");
    	}
    	
    	//Driver and Vehicle both exist--check if the driver has any existing reservations
    	List<Reservation> reservations = driver.getReservations();
    	System.out.println("Reservations size before booking " + reservations.size());
    	
    	for (Reservation r : reservations) {
    	    if(r.getStatus() == ReservationStatus.CURRENT || r.getStatus() == ReservationStatus.UPCOMING) {
    	    	System.out.println("The user already has a reservation. Please rebook after first reservation is completed.");
    	    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You already have an existing Reservation. Please retry after it ends");
    	    }
    	}
    	
    	if(vehicle.getStatus() != VehicleStatus.AVAILABLE) {
    		
    		System.out.println("The vehicle is already booked. Please rebook with another vehicle.");
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("We're sorry, this vehicle is booked. Please retry booking with another one.");
    	}
    		
    	
    	//If everything is green, go ahead and save reservation
    	reservation.setDriver(driver);
    	reservation.setVehicle(vehicle);
    	reservation.setPrice();
    	vehicle.setStatus(VehicleStatus.BOOKED);
    	reservation.setStatus(ReservationStatus.UPCOMING);
        return ResponseEntity.ok(reservationRepository.save(reservation));
    }
    
    
    
    
    //API to cancel a reservation--driver reservation list will be updated, vehicle status changed to Available
    //If cancelled within an hour from pickup, one hour's price will be added to final price
    @PutMapping("/reservation/{reservationId}/cancel")
    public ResponseEntity<?> cancelReservation(@PathVariable Long reservationId) {
    	Optional<Reservation> r = reservationRepository.findById(reservationId);
    	
    	if(r.isPresent()) {
    		Reservation reservation = r.get();
    		if(reservation.getStatus() == ReservationStatus.UPCOMING) {
    			
    			Vehicle vehicle = reservation.getVehicle();
    			
    			Calendar cancelCal = Calendar.getInstance();
		        Date cancelTime = cancelCal.getTime();
		        
		        Calendar pickupCal = Calendar.getInstance();
		        pickupCal.setTime(reservation.getPickup());
		        
		        //Add 1 to pickup time to check if 
		        pickupCal.add(Calendar.HOUR_OF_DAY, -1);
		        Date pickupTime = pickupCal.getTime();
		        
		        //System.out.println("DATE COMPARE OUTPUT " + returnTime.compareTo(pickupTime));

		        if(cancelTime.compareTo(pickupTime) <= 0) {
		        	//cancel occurs within 1 hour of designated pickup time
		        	System.out.println("CAME IN HERE");
		        	reservation.addLateFee(vehicle.getVehicleBasePrice());
		        }
		        
	    		vehicle.setStatus(VehicleStatus.AVAILABLE);
	    		reservation.setStatus(ReservationStatus.CANCELLED);
	    		return ResponseEntity.ok(reservationRepository.save(reservation));
    		} else {
    			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Trying to cancel a reservation that has already started/ended " + reservation.getStatus());
    		}
    	} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Reservation with id = " + reservationId + " not found");
    	}
    	
    }
    
    
    //API to change status of reservation when driver picks vehicle up
    @PutMapping("/reservation/{reservationId}/current")
    public ResponseEntity<?> currentReservation(@PathVariable Long reservationId) {
    	Optional<Reservation> r = reservationRepository.findById(reservationId);
    	
    	if(r.isPresent()) {
    		Reservation reservation = r.get();
    		if(reservation.getStatus() == ReservationStatus.UPCOMING) {
				reservation.setStatus(ReservationStatus.CURRENT);
				ParkingLocation location = reservation.getVehicle().getParkingLocation();
				location.setFilledSpots(location.getFilledSpots() - 1);
				Date d = new Date();
				reservation.setPickup(d);
				System.out.println("Driver successfully picked up vehicle on reservation" + reservation.getReservationId());
				return ResponseEntity.ok(reservationRepository.save(reservation));
    		} else {
    			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Trying update reservation with status " + reservation.getStatus());
    		}
    	} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Reservation with id = " + reservationId + " not found");
    	}
    	
    }
    
    //API to change status of reservation when driver drops back a vehicle
    //Calculate and add late fee to price of reservation when a vehicle is returned late 
    @PutMapping("/reservation/{reservationId}/end")
    public ResponseEntity<?> endReservation(@PathVariable Long reservationId) {
    	Optional<Reservation> r = reservationRepository.findById(reservationId);
    	boolean latefee = false;
    	
    	if(r.isPresent()) {
    		Reservation reservation = r.get();
    		if(reservation.getStatus() == ReservationStatus.CURRENT) {

    			//Check if vehicle can be dropped at a location
    			Vehicle vehicle = reservation.getVehicle();
    			ParkingLocation location = vehicle.getParkingLocation();
    			if(location.getFilledSpots() < location.getCapacity()) {
    				location.setFilledSpots(location.getFilledSpots() + 1);
    				vehicle.setStatus(VehicleStatus.AVAILABLE);
    				
    				//Check if vehicles was returned on time
    				Calendar returnCal = Calendar.getInstance();
    		        Date returnTime = returnCal.getTime();
    		        
    		        //Add booked number of hours to originally booked pickup date
    		        Calendar pickupCal = Calendar.getInstance();
    		        pickupCal.setTime(reservation.getPickup());
    		        pickupCal.add(Calendar.HOUR_OF_DAY, reservation.getHours());
    		        Date pickupTime = pickupCal.getTime();
    		        
    		        System.out.println("DATE COMPARE OUTPUT " + returnTime.compareTo(pickupTime));

    		        if(returnTime.compareTo(pickupTime) <= 0) {
    		        	//return occurs after designated pickup time
    		        	System.out.println("CAME IN HERE");
    		        	reservation.addLateFee(100);
    		        	latefee = true;
    		        }
    		        
    				System.out.println("Driver successfully dropped off vehicle at location " + reservation.getVehicle().getParkingLocation().getLocationId());
    				if(latefee) 
    					System.out.println("Late fee: 100$ Balance pay: 100$");
    				
    				reservation.setStatus(ReservationStatus.ENDED);
    				return ResponseEntity.ok(reservationRepository.save(reservation));
    				
    			} else {
    				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot drop vehicle at an already full location");
    			}
    		} else {
    			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("YOu are trying to update an incorrect reservation");
    		}

    	} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Reservation with id = " + reservationId + " not found");
    	}
    	
    }
    
    //Basic Put operation--will probably never be used
    @PutMapping("/reservation/{reservationId}")
    public ResponseEntity<?> updateReservation(@PathVariable Long reservationId, @RequestBody Reservation reservation) {
        return reservationRepository.findById(reservationId).map(newReservation -> {
            newReservation.setDriver(reservation.getDriver());
            newReservation.setVehicle(reservation.getVehicle());
            newReservation.setPickup(reservation.getPickup());
            newReservation.setHours(reservation.getHours());
            newReservation.setPicked(reservation.isPicked());
            newReservation.setPrice();
            newReservation.setStatus(reservation.getStatus());
            return ResponseEntity.ok(reservationRepository.save(reservation));
        }).orElseThrow(() -> new ResourceNotFoundException("Reservation with id = " + reservationId + " not found"));
    }
    
    
    //Krutika Do not test, has bugs: driver reservation list has to be updated, vehicle status has to be changed 
    //Krutika Will probably never be used--write a cancel function instead that changes reservation status
    @DeleteMapping("/reservation/{reservationId}")
	public ResponseEntity<?> deleteReservation(@PathVariable Long reservationId) {
    	
    	//need to add logic to free reserved vehicle up, car returned, review etc
		if (reservationRepository.existsById(reservationId)) {
			Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
			Vehicle vehicle = reservation.getVehicle();
			vehicle.setStatus(VehicleStatus.AVAILABLE);
			reservationRepository.deleteById(reservationId);
			return ResponseEntity.ok("Reservation with " + reservationId + " deleted");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vehicle with " + reservationId + " not found");

	}    
	 
}