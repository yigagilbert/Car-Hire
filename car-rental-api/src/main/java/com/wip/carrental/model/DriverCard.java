package com.wip.carrental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.NonNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import javax.persistence.*;


@Entity
@Table

@ApiModel(description = "This is DriverCard Table")
public class DriverCard {

	@Id
	private Long driverCardNumber;

	@Column
	@NonNull
	private Integer driverCardCvv;

	@Column
	@NonNull
	private Date driverCardExpiry;


	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Driver driver;

	public Long getDriverCardNumber() {
		return driverCardNumber;
	}

	public void setDriverCardNumber(Long driverCardNumber) {
		this.driverCardNumber = driverCardNumber;
	}

	public Integer getDriverCardCvv() {
		return driverCardCvv;
	}

	public void setDriverCardCvv(Integer driverCardCvv) {
		this.driverCardCvv = driverCardCvv;
	}

	@NonNull
	public Date getDriverCardExpiry() {
		return driverCardExpiry;
	}

	public void setDriverCardExpiry(@NonNull Date driverCardExpiry) {
		this.driverCardExpiry = driverCardExpiry;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}
}
