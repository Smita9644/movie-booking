package com.app.dao;

import java.util.List;
import java.util.Optional;
import com.app.exception.InvalidDataException;
import com.app.exception.NotFoundException;
import com.app.pojo.Movie;

public interface MovieService {
	/**
	 * Here we are creating movie with auto generated movie_id
	 * 
	 * @param movie
	 * @return we return movie object
	 */
	public Movie addMovie(Movie movie) throws InvalidDataException;

	/**
	 * This method is for getting list of all the movies from table
	 * 
	 * @return list of movies.(if not present then empty list.)
	 */
	public List<Movie> getMovies();

	/**
	 * This method is for getting all the movies from table which have same category
	 * 
	 * @param String category name
	 * @return List of movies(if not present then empty list)
	 */
	public List<Movie> getAllMoviesOfSelectedCategory(String category) throws InvalidDataException;

	/**
	 * This method is for getting all the movies from table which have same language
	 * 
	 * @param String language name
	 * @return List of movies(if not present then empty list)
	 */
	public List<Movie> getAllMoviesOfSelectedLanguage(String language) throws InvalidDataException;

	/**
	 * This method is for getting all the movies from table which have same format
	 * 
	 * @param String format name
	 * @return List of movies(if not present then empty list)
	 */
	public List<Movie> getAllMoviesOfSelectedformat(String format) throws InvalidDataException;

	/**
	 * This method is for getting all the movies from table which have same
	 * format,language and category
	 * 
	 * @param String format,language and format
	 * @return List of movies(if not present then empty list)
	 */
	public List<Movie> getAllMoviesOfSelectedType(String category, String language, String format)
			throws InvalidDataException;

	/**
	 * This method is for getting all the movies from table which are from same
	 * category and language
	 * 
	 * @param String category and language
	 * @return List of movies(if not present then empty list)
	 */
	public List<Movie> getAllMovieOfSelectedCategoryAndLAnguage(String category, String language)
			throws InvalidDataException;

	/**
	 * This method is for getting all the movies from table which are from same
	 * category and format
	 * 
	 * @param String category and format
	 * @return List of movies(if not present then empty list)
	 */
	public List<Movie> getAllMovieOfSelectedCategoryAndFormat(String category, String format)
			throws InvalidDataException;

	/**
	 * This method is for getting all the movies from table which are from same
	 * format and language
	 * 
	 * @param String format and language
	 * @return List of movies(if not present then empty list)
	 */
	public List<Movie> getAllMovieOfSelectedLanguageAndFormat(String language, String format)
			throws InvalidDataException;

	/**
	 * This method is for getting list of distinct category from table
	 * 
	 * @return List of String having distinct category else empty list
	 */
	public List<String> getAllcategory();

	/**
	 * This method is for getting list of distinct language from table
	 * 
	 * @return List of String having distinct language else empty list
	 */
	public List<String> getAllLanguage();

	/**
	 * This method is for getting list of distinct format from table
	 * 
	 * @return List of String having distinct format else empty list
	 */
	public List<String> getAllFormat();

	/**
	 * in this method we are updating the specific movie from table
	 * 
	 * @param movie
	 * @return we return movie object
	 */
	public Movie updateMovie(Movie movie) throws InvalidDataException;

	/**
	 * delete the movie record by movie_id.
	 * 
	 * @param movieId
	 * @return movie with give id movieId deleted(if user will found). otherwise
	 * @throws NotFoundException
	 */
	public String deletMovie(int movieId) throws InvalidDataException, NotFoundException;

	/**
	 * Find the movie record by movie_id.
	 * 
	 * @param movieId
	 * @return movie record (if movie will found). otherwise
	 * @throws NotFoundException
	 */
	Movie getDetailsOfSelectedMovie(int movieId) throws NotFoundException;

}