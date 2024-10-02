package com.wip.carrental.repository;

import com.wip.carrental.model.Driver;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DriverRepository extends CrudRepository<Driver, String> {

}
