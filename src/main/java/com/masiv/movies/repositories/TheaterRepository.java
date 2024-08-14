package com.masiv.movies.repositories;

import org.springframework.data.repository.CrudRepository;

import com.masiv.movies.models.Theater;

public interface TheaterRepository extends CrudRepository<Theater, Long> {}
