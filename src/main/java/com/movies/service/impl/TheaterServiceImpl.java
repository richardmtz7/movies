package com.movies.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movies.models.Theater;
import com.movies.repositories.ITheaterRepository;
import com.movies.service.TheaterService;
import com.movies.validation.TheaterValidator;

@Service
final public class TheaterServiceImpl implements TheaterService{
	@Autowired
	private ITheaterRepository iTheaterRepository;
	@Override
	public Theater createTheater(Theater theater) throws Exception {
		TheaterValidator.theaterValidator(theater);
		try {
			iTheaterRepository.save(theater);
			
			return theater;
		} catch (Exception e) {
			throw new Exception("Error creating theater" + e);
		}
	}
	@Override
	public void updateAvailableSeats(Theater theater) throws Exception {
		TheaterValidator.theaterExistValidator(theater);
        theater.setAvailableSeats(theater.getAvailableSeats());
        try {
        	iTheaterRepository.save(theater);
		} catch (Exception e) {
			throw new Exception("Error updating seats" + e);
		}
	}
	@Override
	public Theater getTheaterById(String theaterId) throws Exception {
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
	@Override
	public List<Theater> getAllTheaters() {
		return (List<Theater>) iTheaterRepository.findAll();
	}
}
