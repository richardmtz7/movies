package com.movies.service;

import java.util.List;

import com.movies.models.Movie;

public interface MovieService {
	public Movie createMovie(Movie movie) throws Exception;
	public List<Movie> getAllMovies();
}
