package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This is BookedSeat repository, It will interact with database using
 * JpaRepository.
 */
public interface BookedSeatRepository extends JpaRepository<com.app.pojo.BookedSeat, Integer> {

}
