package com.masiv.movies.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masiv.movies.models.Theater;
import com.masiv.movies.repositories.TheaterRepository;
import com.masiv.movies.validation.TheaterValidator;

@Service
public class TheaterService {
	@Autowired
	private TheaterRepository theaterRepository;
	public Theater createTheater(Theater theater) {
		TheaterValidator.theaterValidator(theater);
		return theaterRepository.save(theater);
	}
}
