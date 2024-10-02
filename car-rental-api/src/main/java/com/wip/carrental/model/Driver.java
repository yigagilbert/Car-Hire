package com.wip.carrental.model;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;


import java.util.*;

@Entity
@Table(name = "driver")

@ApiModel(description = "User Table ")
public class Driver {

    @Id
    @NonNull
    private String driverEmailId;

    @Column
    @NonNull
    private String driverLicense;

    @Column
    private String driverAddress;
    @Column
    @NonNull
    private String driverName;

    @Column
    @NonNull
    private String driverPassword;
    @Column(nullable = false, updatable = false)
    private Date driverMembershipStart;
    @Column(nullable = false)
    private Date driverMembershipEnd;
    
    
    //Do not provide this value in POST data, should only be changed via admin login
    @Column
    private Double memberShipFee = 50.0;
    
    @Column
    private boolean member = true;
    
    
    @OneToMany(fetch = FetchType.EAGER, cascade =  CascadeType.ALL,
            mappedBy = "driver")
    @JsonIgnoreProperties("driver")
    private List<Reservation> reservations;


    @NonNull
    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(@NonNull String driverName) {
        this.driverName = driverName;
    }

    @NonNull
    public String getDriverPassword() {
        return driverPassword;
    }

    public void setDriverPassword(@NonNull String driverPassword) {
        this.driverPassword = driverPassword;
    }

    public Date getDriverMembershipStart() {
        return driverMembershipStart;
    }

    public void setDriverMembershipStart(Date driverMembershipStart) {
        this.driverMembershipStart = driverMembershipStart;
    }

    public Date getDriverMembershipEnd() {
        return driverMembershipEnd;
    }

    public void setDriverMembershipEnd(Date driverMembershipEnd) {
        this.driverMembershipEnd = driverMembershipEnd;
    }

    //@JsonIgnore
    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
    
    public void addReservation(Reservation reservation) {
    	reservations.add(reservation);
    }

    @NonNull
    public String getDriverEmailId() {
        return driverEmailId;
    }

    public void setDriverEmailId(@NonNull String driverEmailId) {
        this.driverEmailId = driverEmailId;
    }

    @NonNull
    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(@NonNull String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getDriverAddress() {
        return driverAddress;
    }

    public void setDriverAddress(String driverAddress) {
        this.driverAddress = driverAddress;
    }

    //This will set start and end of the membership
    public void setdMembership() {
        Calendar cal = Calendar.getInstance();
        Date result = cal.getTime();
        this.driverMembershipStart = result;
        cal.add(Calendar.MONTH, 6);
        result = cal.getTime();
        this.driverMembershipEnd = result;
    }

	public Double getMemberShipFee() {
		return memberShipFee;
	}

	public void setMemberShipFee(Double memberShipFee) {
		this.memberShipFee = memberShipFee;
	}

	public boolean isMember() {
		return member;
	}

	public void setMember(boolean member) {
		this.member = member;
	}


}
