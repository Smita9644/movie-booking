package com.app.dao;

import java.util.List;

import com.app.exception.InvalidDataException;
import com.app.exception.NotFoundException;

public interface BookingService {
	/**
	 * In this method we are trying to book the movie depending upon userId and
	 * screen id and the no's of selected list
	 * @param userid
	 * @param screenId
	 * @param seat
	 * @return String "movie is booked"(if successful)
	 * if user or screen id is incorrect @throws NotFoundException
	 */
	public String bookTheMovie(int userId, int screenId, List<Integer> seat) throws InvalidDataException, NotFoundException;

}
