package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.MovieService;
import com.app.exception.InvalidDataException;
import com.app.exception.NotFoundException;
import com.app.pojo.Movie;
import com.app.validation.ValidateMovie;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "MovieControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
/** Using this annotation we mark this class as controller */
@RestController
public class MovieController {
	/** auto wired movie service for accessing the methods of MovieService */
	@Autowired
	MovieService movieService;
	/**
	 * We create object of ValidateMovie to apply validation to all fields of movie
	 * class
	 */
	ValidateMovie validateMovie = new ValidateMovie();

	/**
	 * Here we are creating movie with auto generated movie_id
	 * 
	 * @param movie
	 * @return we return movie object
	 */

	@ApiOperation("create a new movie")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "movie created", response = Movie.class) })
	@PostMapping(value = "/upload")
	public Movie addMovie(@RequestBody Movie movie) throws InvalidDataException {
		// Here we validate all parameters of request body
		validateMovie.validateMovie(movie);
		return movieService.addMovie(movie);

	}

	/**
	 * in this method we are updating the specific movie from table
	 * 
	 * @param movie
	 * @return we return movie object
	 */

	@ApiOperation("updated the movie")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "movie updated", response = Movie.class) })
	@PutMapping("/updateMovie")
	public Movie updateMovie(@RequestBody Movie movie) throws InvalidDataException {
		// Here we validate all parameters of request body
		validateMovie.validateMovie(movie);
		return movieService.updateMovie(movie);
	}

	/**
	 * This method is for getting list of all the movies from table
	 * 
	 * @return list of movies.(if not present then empty list.)
	 */
	@ApiOperation("Get list of all the movies")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "All movie details", response = Movie.class) })
	@GetMapping("/movies")
	public List<Movie> getAllMovies() {
		return movieService.getMovies();
	}

	/**
	 * This method is for getting all the movies from table which have same category
	 * 
	 * @param String category name
	 * @return List of movies(if not present then empty list)
	 */
	@ApiOperation("Filter the movies depending upon category")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "All movies of selected category", response = Movie.class) })
	@GetMapping("/bycategory")
	public List<Movie> getAllMoviesOfSelectedCategory(@RequestParam("category") String category)
			throws InvalidDataException {
		// Here we validate request parameter category
		validateMovie.validateCategory(category);
		return movieService.getAllMoviesOfSelectedCategory(category);
	}

	/**
	 * This method is for getting all the movies from table which have same language
	 * 
	 * @param String language name
	 * @return List of movies(if not present then empty list)
	 */
	@GetMapping("/bylanguage")
	@ApiOperation("Filter the movies depending upon language")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "All movies of selected language", response = Movie.class) })
	public List<Movie> getAllMoviesOfSelectedLanguage(@RequestParam("language") String language)
			throws InvalidDataException {
		// Here we validate request parameter language
		validateMovie.validateLanguage(language);
		return movieService.getAllMoviesOfSelectedLanguage(language);
	}

	/**
	 * This method is for getting all the movies from table which have same format
	 * 
	 * @param String format name
	 * @return List of movies(if not present then empty list)
	 */
	@ApiOperation("Filter the movies depending upon format")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "All movies of selected format", response = Movie.class) })
	@GetMapping("/byformat")
	public List<Movie> getAllMoviesOfSelectedformat(@RequestParam("format") String format) throws InvalidDataException {
		// Here we validate request parameter format
		validateMovie.validateFormat(format);
		return movieService.getAllMoviesOfSelectedformat(format);
	}

	@ApiOperation("Filter the movies depending upon language,category and format")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "All movies of selected language,category and format", response = Movie.class) })
	@GetMapping("/byall")
	public List<Movie> getAllMoviesOfSelectedType(@RequestParam("category") String category,
			@RequestParam("language") String language, @RequestParam("format") String format)
			throws InvalidDataException {
		// Here we validate all request parameter
		validateMovie.validateCategory(category);
		validateMovie.validateFormat(format);
		validateMovie.validateLanguage(language);
		return movieService.getAllMoviesOfSelectedType(category, language, format);
	}

	/**
	 * This method is for getting list of distinct category from table
	 * 
	 * @return List of String having distinct category else empty list
	 */
	@ApiOperation("Get All disticnt category from movies")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "All distinct categories", response = String.class) })
	@GetMapping("/category")
	public List<String> getAllCategory() {
		return movieService.getAllcategory();
	}

	/**
	 * This method is for getting list of distinct language from table
	 * 
	 * @return List of String having distinct language else empty list
	 */
	@ApiOperation("Get All disticnt languages from movies")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "All distinct languages", response = String.class) })
	@GetMapping("/language")
	public List<String> getAllLanguage() {
		return movieService.getAllLanguage();
	}

	/**
	 * This method is for getting list of distinct format from table
	 * 
	 * @return List of String having distinct format else empty list
	 */
	@GetMapping("/format")
	@ApiOperation("Get All disticnt formats from movies")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "All distinct formats", response = String.class) })
	public List<String> getAllFormat() {
		return movieService.getAllFormat();
	}

	/**
	 * This method is for getting all the movies from table which are from same
	 * category and language
	 * 
	 * @param String category and language
	 * @return List of movies(if not present then empty list)
	 */
	@GetMapping("/bycategoryLanguage")
	@ApiOperation("Filter movies by category and language")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "All movies of selected language and category", response = Movie.class) })
	public List<Movie> getAllMoviesOfSelectedCategoryAndLanguage(@RequestParam("category") String category,
			@RequestParam("language") String language) throws InvalidDataException {
		// Here we validate request parameter language and category
		validateMovie.validateCategory(category);
		validateMovie.validateLanguage(language);
		return movieService.getAllMovieOfSelectedCategoryAndLAnguage(category, language);
	}

	/**
	 * This method is for getting all the movies from table which are from same
	 * category and format
	 * 
	 * @param String category and format
	 * @return List of movies(if not present then empty list)
	 */
	@GetMapping("/bycategoryFormat")
	@ApiOperation("Filter movies by category and format")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "All movies of selected format and category", response = Movie.class) })
	public List<Movie> getAllMoviesOfSelectedCategoryAndFormat(@RequestParam("category") String category,
			@RequestParam("format") String format) throws InvalidDataException {
		// Here we validate request parameter category and format
		validateMovie.validateCategory(category);
		validateMovie.validateFormat(format);
		return movieService.getAllMovieOfSelectedCategoryAndFormat(category, format);
	}

	/**
	 * This method is for getting all the movies from table which are from same
	 * format and language
	 * 
	 * @param String format and language
	 * @return List of movies(if not present then empty list)
	 */
	@GetMapping("/byFormatLanguage")
	@ApiOperation("Filter movies by format and language")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "All movies of selected language and format", response = Movie.class) })
	public List<Movie> getAllMoviesOfSelectedFormatAndLanguage(@RequestParam("format") String format,
			@RequestParam("language") String language) throws InvalidDataException {
		// Here we validate request parameter format and language
		validateMovie.validateFormat(format);
		validateMovie.validateLanguage(language);
		return movieService.getAllMovieOfSelectedLanguageAndFormat(language, format);
	}

	/**
	 * Find the movie record by movie_id.
	 * 
	 * @param movieId
	 * @return movie record (if movie will found). otherwise
	 * @throws NotFoundException
	 */
	@GetMapping("/movie/{movieId}")
	@ApiOperation("Get details of movie by id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "movie details", response = Movie.class) })
	public Movie getDetailsOfSelectedMovie(@PathVariable("movieId") int id) throws NotFoundException {
		return movieService.getDetailsOfSelectedMovie(id);

	}

	/**
	 * delete the movie record by movie_id.
	 * 
	 * @param movieId
	 * @return movie with give id movieId deleted(if user will found). otherwise
	 * @throws NotFoundException
	 */
	@DeleteMapping("/deleteMovie/{movieId}")
	@ApiOperation("Delete given movie")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "movie deleted", response = Movie.class) })
	public String deleteMovie(@PathVariable("movieId") int id) throws InvalidDataException, NotFoundException {
		return movieService.deletMovie(id);
	}
}