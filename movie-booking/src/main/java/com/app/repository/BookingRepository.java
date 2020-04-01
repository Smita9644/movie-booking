package com.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.app.pojo.Booking;

/**
 * This is Booking repository, It will interact with database using
 * JpaRepository.
 */
public interface BookingRepository extends CrudRepository<Booking, Integer> {

}
