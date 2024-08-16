package com.masiv.movies.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.masiv.movies.models.Theater;

@Repository
public interface ITheaterRepository extends CrudRepository<Theater, String> {}
