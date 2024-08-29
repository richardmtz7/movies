package com.movies.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.movies.models.Function;

@Repository
public interface IFunctionRepository extends CrudRepository<Function, String> {}
