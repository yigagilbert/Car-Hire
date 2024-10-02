package com.wip.carrental.repository;

import com.wip.carrental.model.ParkingLocation;
import com.wip.carrental.model.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
    ArrayList<Vehicle> findAllByParkingLocation(ParkingLocation parkingLocation);
}