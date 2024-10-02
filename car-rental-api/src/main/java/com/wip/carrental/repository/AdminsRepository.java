package com.wip.carrental.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wip.carrental.model.Admin;

@Repository
public interface AdminsRepository extends CrudRepository<Admin, String> {

}
