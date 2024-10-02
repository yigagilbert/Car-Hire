package com.wip.carrental.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.wip.carrental.model.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer>{

}
