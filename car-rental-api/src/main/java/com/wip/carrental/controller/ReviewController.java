package com.wip.carrental.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wip.carrental.controller.exceptions.ResourceNotFoundException;
import com.wip.carrental.model.Reservation;
import com.wip.carrental.model.Review;
import com.wip.carrental.repository.ReservationRepository;
import com.wip.carrental.repository.ReviewRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	//Get reviews--might not be used at all
	@GetMapping("/reviews")
	public List<Review> getAllReviews() {
		return (List<Review>) reviewRepository.findAll();	
	}
	
	@PostMapping("/reviews") 
	public ResponseEntity<?> postReview(@RequestBody Review review, @RequestParam(value = "reservation_id") Long reservationId) {
		
		Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
		if( reservation != null) {
			review.setReservation(reservation);
			reservation.setReview(review);
			return ResponseEntity.ok(reviewRepository.save(review));
		}
		else {
			throw new ResourceNotFoundException("Reservation ID " + reservationId + " not found");
		}
		
		
	}
	
}
