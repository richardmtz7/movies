package com.masiv.movies.validation;

import com.masiv.movies.models.Movie;

public class MovieValidator {
	public static void validateMovie(Movie movie) {
		if (movie.getTitle() == null || movie.getTitle().isEmpty()) {
			throw new IllegalArgumentException("The title cannot be empty");
		}
		if (movie.getDuration() == null || movie.getDuration() <= 0) {
			throw new IllegalArgumentException("The duration should be positive");
		}
	}
}
