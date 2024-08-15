package com.masiv.movies.service.impl;

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
	public Movie createMovie(Movie movie) {
		MovieValidator.validateMovie(movie);
		return iMovieRepository.save(movie);
	}

}
