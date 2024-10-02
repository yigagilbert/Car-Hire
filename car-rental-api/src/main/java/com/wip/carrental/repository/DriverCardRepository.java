package com.wip.carrental.repository;

import com.wip.carrental.model.Driver;
import com.wip.carrental.model.DriverCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverCardRepository extends CrudRepository<DriverCard, Long> {
    List<DriverCard> findDriverCardsByDriver(Driver driver);

    Optional<DriverCard> findByDriverAndDriverCardNumber(Driver driver, Long dcNumber);

}
