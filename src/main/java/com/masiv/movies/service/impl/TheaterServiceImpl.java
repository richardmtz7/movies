package com.masiv.movies.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masiv.movies.models.Theater;
import com.masiv.movies.repositories.ITheaterRepository;
import com.masiv.movies.service.TheaterService;
import com.masiv.movies.validation.TheaterValidator;

@Service
final public class TheaterServiceImpl implements TheaterService{
	@Autowired
	private ITheaterRepository iTheaterRepository;
	@Override
	public Theater createTheater(Theater theater) {
		TheaterValidator.theaterValidator(theater);
		return iTheaterRepository.save(theater);
	}
	@Override
	public void updateAvailableSeats(Theater theater) throws Exception {
		TheaterValidator.theaterExistValidator(theater);
        theater.setAvailableSeats(theater.getAvailableSeats());
        iTheaterRepository.save(theater);
	}
	@Override
	public Theater getTheaterById(Long theaterId) throws Exception {
		return iTheaterRepository.findById(theaterId)
                .orElseThrow(() -> new Exception("Theater not found"));
	}
	@Override
	public void seatAvailables(Theater theater) throws Exception {
		TheaterValidator.theaterExistValidator(theater);
		if (theater.getAvailableSeats() == 0) {
			throw new Exception("Seats unavailable"); 
		}
	}
}
