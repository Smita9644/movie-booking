package com.app.dao;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.app.exception.InvalidDataException;
import com.app.exception.NotFoundException;
import com.app.pojo.Movie;
import com.app.pojo.Screen;
import com.app.pojo.Seat;
import com.app.pojo.User;
import com.app.repository.MovieRepository;
import com.app.repository.ScreenRepository;
import com.app.repository.SeatRepository;
import com.app.validation.ValidateMovie;

@Transactional
@Repository
public class MovieServiceImpl implements MovieService {

	/**
	 * auto wired ScreenRepository for accessing the methods of screen repository
	 */
	@Autowired
	private ScreenRepository screenRepository;

	/** auto wired SeatRepository for accessing the methods of seat repository */
	@Autowired
	private SeatRepository seatRepository;

	/** auto wired MovieRepository for accessing the methods of movie repository */
	@Autowired
	MovieRepository movieRepository;

	/**
	 * Here we are creating movie with auto generated movie_id
	 * 
	 * @param movie
	 * @return we return movie object
	 */
	@Override
	public Movie addMovie(Movie movie) throws InvalidDataException {
		return this.movieRepository.save(movie);

	}

	/**
	 * This method is for getting list of all the movies from table
	 * 
	 * @return list of movies.(if not present then empty list.)
	 */
	@Override
	public List<Movie> getMovies() {
		// TODO Auto-generated method stub
		return (List<Movie>) this.movieRepository.findAll();
	}

	/**
	 * This method is for getting all the movies from table which have same category
	 * 
	 * @param String category name
	 * @return List of movies(if not present then empty list)
	 */
	@Override
	public List<Movie> getAllMoviesOfSelectedCategory(String category) throws InvalidDataException {
		return movieRepository.getAllMoviesOfSelectedCategory(category);

	}

	/**
	 * This method is for getting all the movies from table which have same language
	 * 
	 * @param String language name
	 * @return List of movies(if not present then empty list)
	 */
	@Override
	public List<Movie> getAllMoviesOfSelectedLanguage(String language) throws InvalidDataException {
		// TODO Auto-generated method stub
		return movieRepository.getAllMoviesOfSelectedLanguage(language);
	}

	/**
	 * This method is for getting list of distinct category from table
	 * 
	 * @return List of String having distinct category else empty list
	 */
	@Override
	public List<String> getAllcategory() {
		return movieRepository.getDistinctCategoty();

	}

	/**
	 * This method is for getting list of distinct language from table
	 * 
	 * @return List of String having distinct language else empty list
	 */
	@Override
	public List<String> getAllLanguage() {
		return movieRepository.getDistinctLanguage();

	}

	/**
	 * This method is for getting list of distinct format from table
	 * 
	 * @return List of String having distinct format else empty list
	 */
	@Override
	public List<String> getAllFormat() {
		return movieRepository.getDistinctFormat();
	}

	/**
	 * This method is for getting all the movies from table which have same format
	 * 
	 * @param String format name
	 * @return List of movies(if not present then empty list)
	 */
	@Override
	public List<Movie> getAllMoviesOfSelectedformat(String format) throws InvalidDataException {
		return movieRepository.getAllMoviesOfSelectedFormat(format);
	}

	/**
	 * This method is for getting all the movies from table which have same
	 * format,language and category
	 * 
	 * @param String format,language and format
	 * @return List of movies(if not present then empty list)
	 */
	@Override
	public List<Movie> getAllMoviesOfSelectedType(String category, String language, String format)
			throws InvalidDataException {
		return movieRepository.getAllMoviesOfSelectedType(category, language, format);

	}

	/**
	 * This method is for getting all the movies from table which are from same
	 * category and language
	 * 
	 * @param String category and language
	 * @return List of movies(if not present then empty list)
	 */
	@Override
	public List<Movie> getAllMovieOfSelectedCategoryAndLAnguage(String category, String language)
			throws InvalidDataException {
		return movieRepository.getAllMoviesOfSelectedCategoryAndLanguage(category, language);
	}

	/**
	 * This method is for getting all the movies from table which are from same
	 * category and format
	 * 
	 * @param String category and format
	 * @return List of movies(if not present then empty list)
	 */
	@Override
	public List<Movie> getAllMovieOfSelectedCategoryAndFormat(String category, String format)
			throws InvalidDataException {
		return movieRepository.getAllMoviesOfSelectedCategoryAndFormat(category, format);
	}

	/**
	 * This method is for getting all the movies from table which are from same
	 * format and language
	 * 
	 * @param String format and language
	 * @return List of movies(if not present then empty list)
	 */
	@Override
	public List<Movie> getAllMovieOfSelectedLanguageAndFormat(String language, String format)
			throws InvalidDataException {
		return movieRepository.getAllMoviesOfSelectedFormatAndLanguage(format, language);
	}

	/**
	 * in this method we are updating the specific movie from table
	 * 
	 * @param movie
	 * @return we return movie object
	 */
	public Movie updateMovie(Movie movie) throws InvalidDataException {
		return this.movieRepository.save(movie);

	}

	/**
	 * delete the movie record by movie_id.
	 * 
	 * @param movieId
	 * @return movie with give id movieId deleted(if user will found). otherwise
	 * @throws NotFoundException
	 */
	@Override
	public String deletMovie(int movieId) throws InvalidDataException, NotFoundException {

		Movie movie = movieRepository.findById(movieId)
				.orElseThrow(() -> new NotFoundException("Unable to find movie with given movie id"));
		List<Screen> screen = screenRepository.getAllShowsOfSelectedMovie(movie.getMovie_id());
		for (Screen sc : screen) {
			int screenId = sc.getScreen_id();
			System.out.println(screenId);
			List<Seat> seats = seatRepository.getAllSeatsOfSelectedScreen(sc);
			for (Seat seat2 : seats) {
				seatRepository.deleteById(seat2.getSeat_id());
			}
			screenRepository.deleteById(screenId);
		}
		try {
			movieRepository.deleteById(movieId);
		} catch (Exception e) {
			throw new NotFoundException("Movie with given id does not exits");
		}
		return "movie with give id " + movieId + " deleted";
	}

	/**
	 * Find the movie record by movie_id.
	 * 
	 * @param movieId
	 * @return movie record (if movie will found). otherwise
	 * @throws NotFoundException
	 */
	@Override
	public Movie getDetailsOfSelectedMovie(int movieId) throws NotFoundException {
		Movie movie = movieRepository.findById(movieId)
				.orElseThrow(() -> new NotFoundException("Unable to find movie with given movie id"));
		return movie;
	}
}