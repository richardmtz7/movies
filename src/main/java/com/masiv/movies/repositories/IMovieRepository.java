package com.masiv.movies.repositories;

import org.springframework.data.repository.CrudRepository;

import com.masiv.movies.models.Movie;

public interface IMovieRepository extends CrudRepository<Movie, Long>{}
