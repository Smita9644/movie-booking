package com.app.controller;

import org.springframework.http.MediaType;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.BookingService;
import com.app.exception.InvalidDataException;
import com.app.exception.NotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "BookingControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
/** Using this annotation we mark this class as controller */
@RestController
public class BookingController {
	/** auto wired booking service for accessing the methods of bookingService */
	@Autowired
	BookingService dao;

	/**
	 * In this method we are trying to book the movie depending upon userId and
	 * screen id and the no's of selected list
	 * 
	 * @param userid
	 * @param screenId
	 * @param seat
	 * @return String "movie is booked"(if successful) if user or screen id is
	 *         incorrect @throws NotFoundException
	 */
	@GetMapping("/book")
	@ApiOperation("Book selected seats with given screenId and userId")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "seats booked", response = String.class) })
	public String bookTheMovie(@RequestParam("userId") int userid, @RequestParam("screenId") int screenId,
			@RequestParam("SeatList") List<Integer> seat) throws InvalidDataException, NotFoundException {
		for (Integer integer : seat) {
			System.out.println(integer);
		}
		return dao.bookTheMovie(userid, screenId, seat);
	}

}
