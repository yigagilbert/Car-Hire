package com.wip.carrental.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.NonNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "reservation")

@ApiModel(description = "All details about the reservations ")
public class Reservation{
	
	@Id
	@GeneratedValue
	private long reservationId;
	

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "driverEmailId", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnoreProperties("reservations")
	private Driver driver; 
	

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="vehicle_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnoreProperties("reservation")
	private Vehicle vehicle;
	
	@Column
	@NonNull
	private Date pickup;
	
	@Column
	@NonNull
	@Max(value = 72)
	private Integer hours;
	
	@Column
	@NonNull
	private boolean picked = false;

	@Column
	@NonNull
	private double price;
	
	@Column
	@NonNull
	private ReservationStatus status = ReservationStatus.UPCOMING;
	
	@OneToOne
	@JoinColumn(name = "reviewId")
	@JsonIgnoreProperties("reservation")
	private Review review;
	

	public long getReservationId() {
		return reservationId;
	}

	public void setReservationId(long reservationId) {
		this.reservationId = reservationId;
	}

	//@JsonIgnore
	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	//@JsonIgnore
	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	

	public Date getPickup() {
		return pickup;
	}

	public void setPickup(Date pickup) {
		this.pickup = pickup;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	public boolean isPicked() {
		return picked;
	}

	public void setPicked(boolean picked) {
		this.picked = picked;
	}

	public double getPrice() {
		return price;
	}

	//Krutika --1$ price discount for every 8 hours extra booked
	public void setPrice() {

		//1$ discount for every 8 hours booked
		double basePrice = this.getVehicle().getVehicleBasePrice();
		int hours = this.getHours();
		this.price = 0;
		
		
		for( int i = 1; i <= hours; i++) {
			this.price += basePrice;
			
			if(i % 8 == 0 && basePrice > 0) 
				basePrice--;
			
			
		}
		
	}
	
	//enforce late fee of 100$ if car is returned late
	public void addLateFee(double lateFee) {
		this.price += lateFee;
	}

	public ReservationStatus getStatus() {
		return status;
	}

	public void setStatus(ReservationStatus status) {
		this.status = status;
	}

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}
	
	
}