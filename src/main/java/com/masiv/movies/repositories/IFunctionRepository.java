package com.masiv.movies.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.masiv.movies.models.Function;

@Repository
public interface IFunctionRepository extends CrudRepository<Function, String> {}
