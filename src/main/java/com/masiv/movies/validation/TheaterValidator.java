package com.masiv.movies.validation;

import com.masiv.movies.models.Theater;

public class TheaterValidator {
	public static void theaterValidator(Theater theater) {
		if(theater.getTheaterName() == null || theater.getTheaterName().isEmpty()) {
			throw new IllegalArgumentException("The theater name cannot be empty");
		}
		if (theater.getCinemaId().isEmpty()) {
			throw new IllegalArgumentException("The cinema cannot be empty");
		}
		if (theater.getTotalSeatingCapacity() == null || theater.getTotalSeatingCapacity() <= 0) {
			throw new IllegalArgumentException("The total seating capacity cannot be zero");
		}
		if (theater.getId().isEmpty()) {
			throw new IllegalArgumentException("The theater cannot be negative");
		}
		if(theater.getAvailableSeats() > theater.getTotalSeatingCapacity()) {
			throw new IllegalArgumentException("The available seats cannot be great than total seats capacity");
		}
		if(theater.getAvailableSeats() == 0) {
			throw new IllegalArgumentException("Unavailable seats");
		}
	}
	public static void theaterExistValidator(Theater theater) throws Exception {
		if(theater.getId().isEmpty()) {
			throw new Exception("Theater not found");
		}
	}
}
