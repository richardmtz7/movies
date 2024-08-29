package com.movies.validation;

import java.util.NoSuchElementException;

import com.movies.models.Function;

public class FunctionValidator {
	public static void functionValidator(Function function) {
		if(function.getStartDate() == null) {
			throw new IllegalArgumentException("The start date must not be null or empty");
		}
		if(function.getEndDate() == null) {
			throw new IllegalArgumentException("The end date must not be null or empty");
		}
		if(function.getAssignedMovie() == null) {
			throw new IllegalArgumentException("The movie assignment must not be null or empty");
		}
		if(function.getAssignedTheater() == null) {
			throw new IllegalArgumentException("The theater assignment must not be null or empty");
		}
		if(function.getEndDate().isBefore(function.getStartDate())) {
			throw new IllegalArgumentException("The end date must be after the start date");
		}
		if(function.getStatusFunction() == 0) {
			throw new NoSuchElementException("The function has already been canceled");
		}
	}
	public static void functionIdValidator(Function function) {
		if (function.getId() == null) {
			throw new IllegalArgumentException("The function has not been registered");
		}
		if(function.getStatusFunction() == 0) {
			throw new NoSuchElementException("The function has already been canceled");
		}
	}
	
}
