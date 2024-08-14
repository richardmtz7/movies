package com.masiv.movies.validation;

import com.masiv.movies.models.Theater;

public class TheaterValidator {
	public static void theaterValidator(Theater theater) {
		if(theater.getTheaterName() == null || theater.getTheaterName().isEmpty()) {
			throw new IllegalArgumentException("The theater name cannot be empty");
		}
		if (theater.getCinemaId() == null || theater.getCinemaId() <= 0) {
			throw new IllegalArgumentException("The cinema id cannot be negative");
		}
		if (theater.getTotalSeatingCapacity() == null || theater.getTotalSeatingCapacity() <= 0) {
			throw new IllegalArgumentException("The total seating capacity cannot be zero");
		}
		if (theater.getTheaterId() == null || theater.getTheaterId() <= 0) {
			throw new IllegalArgumentException("The theater cannot be negative");
		}
	}
}
