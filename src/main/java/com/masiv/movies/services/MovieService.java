package com.masiv.movies.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masiv.movies.models.Movie;
import com.masiv.movies.repositories.MovieRepository;
import com.masiv.movies.validation.MovieValidator;

@Service
public class MovieService {
	@Autowired
	private MovieRepository movieRepository;
	public Movie createMovie(Movie movie) {
		MovieValidator.validateMovie(movie);
		return movieRepository.save(movie);
	}
}
