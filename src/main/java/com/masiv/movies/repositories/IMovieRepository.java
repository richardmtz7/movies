package com.masiv.movies.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.masiv.movies.models.Movie;

@Repository
public interface IMovieRepository extends CrudRepository<Movie, String>{}
