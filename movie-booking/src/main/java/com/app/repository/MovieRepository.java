package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.pojo.Movie;

/**
 * This is Movie repository, It will interact with database using JpaRepository.
 */
public interface MovieRepository extends CrudRepository<Movie, Integer> {

	/**
	 * This find all movies by category
	 * 
	 * @param String category
	 * @return List of movies
	 */
	@Query(value = "SELECT  * FROM Movie m WHERE m.movie_category = :category", nativeQuery = true)
	List<Movie> getAllMoviesOfSelectedCategory(String category);

	/**
	 * This find all movies by language
	 * 
	 * @param String language
	 * @return List of movies
	 */
	@Query(value = "SELECT  * FROM Movie m WHERE m.movie_language = :language", nativeQuery = true)
	List<Movie> getAllMoviesOfSelectedLanguage(String language);

	/**
	 * This find all movies by format
	 * 
	 * @param String format
	 * @return List of movies
	 */
	@Query(value = "SELECT  * FROM Movie m WHERE m.movie_format = :format", nativeQuery = true)
	List<Movie> getAllMoviesOfSelectedFormat(String format);

	/**
	 * This find all movies by category and language
	 * 
	 * @param String category and language
	 * @return List of movies
	 */
	@Query(value = "SELECT * FROM Movie m WHERE  m.movie_language=:language and m.movie_category=:category", nativeQuery = true)
	List<Movie> getAllMoviesOfSelectedCategoryAndLanguage(String category, String language);

	/**
	 * This find all movies by category and format
	 * 
	 * @param String category and format
	 * @return List of movies
	 */
	@Query(value = "SELECT * FROM Movie m WHERE m.movie_format = :format and  m.movie_category=:category", nativeQuery = true)
	List<Movie> getAllMoviesOfSelectedCategoryAndFormat(String category, String format);

	/**
	 * This find all movies by format and language
	 * 
	 * @param String category and format
	 * @return List od movies
	 */
	@Query(value = "SELECT * FROM Movie m WHERE m.movie_format = :format and m.movie_language=:language", nativeQuery = true)
	List<Movie> getAllMoviesOfSelectedFormatAndLanguage(String format, String language);

	/**
	 * Find all the distinct category
	 * 
	 * @return List of String
	 */
	@Query(value = "select  DISTINCT  c.movie_category  from Movie c", nativeQuery = true)
	List<String> getDistinctCategoty();

	/**
	 * Find all the distinct language
	 * 
	 * @return List of String
	 */
	@Query(value = "select  DISTINCT  c.movie_language  from Movie c", nativeQuery = true)
	List<String> getDistinctLanguage();

	/**
	 * Find all the distinct format
	 * 
	 * @return List of String
	 */
	@Query(value = "select  DISTINCT  c.movie_format  from Movie c", nativeQuery = true)
	List<String> getDistinctFormat();

	/**
	 * This find all movies by category,format and language
	 * 
	 * @param String category,format and language
	 * @return List of movies
	 */
	@Query(value = "SELECT * FROM Movie m WHERE m.movie_format = :format and m.movie_language=:language and m.movie_category=:category", nativeQuery = true)
	List<Movie> getAllMoviesOfSelectedType(String category, String language, String format);

}
