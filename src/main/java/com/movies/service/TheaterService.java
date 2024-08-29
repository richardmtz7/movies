package com.movies.service;

import java.util.List;

import com.movies.models.Theater;

public interface TheaterService {
	public Theater createTheater(Theater theater) throws Exception;
	public void updateAvailableSeats(Theater theater) throws Exception;
	public Theater getTheaterById(String id) throws Exception;
	public List<Theater> getAllTheaters();
	public void seatAvailables(Theater theater) throws Exception;
}
