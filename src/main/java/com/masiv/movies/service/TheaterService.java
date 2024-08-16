package com.masiv.movies.service;

import com.masiv.movies.models.Theater;

public interface TheaterService {
	public Theater createTheater(Theater theater) throws Exception;
	public void updateAvailableSeats(Theater theater) throws Exception;
	public Theater getTheaterById(String id) throws Exception;
	public void seatAvailables(Theater theater) throws Exception;
}
