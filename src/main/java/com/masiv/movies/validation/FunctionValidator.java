package com.masiv.movies.validation;

import com.masiv.movies.models.Function;

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
		if(function.getEndDate().before(function.getStartDate())) {
			throw new IllegalArgumentException("The end date must be after the start date");
		}
	}
	public static void functionIdValidator(Function function) {
		if (function.getId() == null) {
			throw new IllegalArgumentException("The function has not been registered");
		}
	}
}
