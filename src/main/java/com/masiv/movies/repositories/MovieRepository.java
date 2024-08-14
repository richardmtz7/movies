package com.masiv.movies.repositories;

import org.springframework.data.repository.CrudRepository;

import com.masiv.movies.models.Movie;

public interface MovieRepository extends CrudRepository<Movie, Long>{}
