package com.masiv.movies.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masiv.movies.models.Movie;
import com.masiv.movies.repositories.IMovieRepository;
import com.masiv.movies.service.MovieService;
import com.masiv.movies.validation.MovieValidator;


@Service
final public class MovieServiceImpl implements MovieService {
	@Autowired
	private IMovieRepository iMovieRepository;
	@Override
	public Movie createMovie(Movie movie) throws Exception {
		MovieValidator.validateMovie(movie);
		try {
			iMovieRepository.save(movie);
		} catch (Exception e) {
			throw new Exception("Error creating movie: " + e);
		}
		
		return movie;
	}
	@Override
	public List<Movie> getAllMovies() {
		return (List<Movie>) iMovieRepository.findAll();
	}
}
