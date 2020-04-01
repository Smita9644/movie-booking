package com.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.exception.InvalidDataException;
import com.app.exception.NotFoundException;
import com.app.pojo.BookedSeat;
import com.app.pojo.Booking;
import com.app.pojo.Screen;
import com.app.pojo.Seat;
import com.app.pojo.User;
import com.app.repository.BookedSeatRepository;
import com.app.repository.BookingRepository;
import com.app.repository.ScreenRepository;
import com.app.repository.SeatRepository;
import com.app.repository.UserRepository;

@Transactional
@Repository
public class BookingServiceImpl implements BookingService {

	/**
	 * auto wired BookingRepository for accessing the methods of booking repository
	 */
	@Autowired
	BookingRepository repository;

	/** auto wired UserRepository for accessing the methods of user repository */
	@Autowired
	private UserRepository userRepository;

	/**
	 * auto wired ScreenRepository for accessing the methods of screen repository
	 */
	@Autowired
	private ScreenRepository screenRepository;

	/** auto wired SeatRepository for accessing the methods of seat repository */
	@Autowired
	private SeatRepository seatRepo;

	/**
	 * auto wired bookedSeatRepository for accessing the methods of booked seat
	 * repository
	 */
	@Autowired
	private BookedSeatRepository bookedSeatRepo;

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
	@Override
	public String bookTheMovie(int userId, int screenId, List<Integer> seats)
			throws InvalidDataException, NotFoundException {

		/** from user id we get the specific user */
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new NotFoundException("Unable to find user by given user id"));

		/** from screen id we get the specific theater */
		Screen screen = screenRepository.findById(screenId)
				.orElseThrow(() -> new NotFoundException("Unable to find screen with given screen id"));

		List<Seat> seatsBooked = new ArrayList<Seat>();
		for (Integer seatId : seats) {
			Seat s = seatRepo.findById(seatId)
					.orElseThrow(() -> new NotFoundException("Unable to find seat with given seat id"));
			seatsBooked.add(s);
		}
		List<Seat> t = seatRepo.getAllSeatsOfSelectedScreen(screen);
		int count = 0;
		for (Seat seat : t) {
			if (seat.isStatus() == false) {
				count++;
			}
		}
		Booking b = new Booking(user);
		System.out.println(count);
		if (seatsBooked.size() <= count) {
			for (Seat seat : seatsBooked) {
				seat.setStatus(true);
			}
			seatRepo.saveAll(seatsBooked);
			repository.save(b);

			for (Seat seat : seatsBooked) {
				System.out.println(seat.toString());
				BookedSeat bookedSeat = new BookedSeat(b, seat);
				bookedSeatRepo.save(bookedSeat);
			}
			return "booked";

		} else {
			return "No seat available for booking";
		}

	}

}
