package com.wip.carrental.repository;

import org.springframework.stereotype.Repository;
import com.wip.carrental.model.Reservation;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

}

