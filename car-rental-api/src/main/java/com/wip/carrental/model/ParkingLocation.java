package com.wip.carrental.model;


import org.springframework.lang.NonNull;

import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "parking_locations")

@ApiModel(description = "All details about the  locations ")
public class ParkingLocation {


    @Id
    @GeneratedValue
    private Long locationId;

    @Column
    @NonNull
    private Integer capacity;
    
    @Column
    private Integer filledSpots = 0;

    @Column
    @NonNull
    private String city;

    @Column
    @NonNull
    private String address;

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    @NonNull
    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(@NonNull Integer capacity) {
        this.capacity = capacity;
    }

    @NonNull
    public String getCity() {
        return city;
    }

    public void setCity(@NonNull String city) {
        this.city = city;
    }

    @NonNull
    public String getAddress() {
        return address;
    }

    public void setAddress(@NonNull String address) {
        this.address = address;
    }

	public Integer getFilledSpots() {
		return filledSpots;
	}

	public void setFilledSpots(Integer filledSpots) {
		this.filledSpots = filledSpots;
	}


}
