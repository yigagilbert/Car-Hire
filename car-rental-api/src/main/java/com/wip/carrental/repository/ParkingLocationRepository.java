package com.wip.carrental.repository;

import com.wip.carrental.model.ParkingLocation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingLocationRepository extends CrudRepository<ParkingLocation, Long> {

}
