package com.movies.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.movies.models.Movie;

@Repository
public interface IMovieRepository extends CrudRepository<Movie, String>{}
