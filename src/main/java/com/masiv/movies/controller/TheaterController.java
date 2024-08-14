package com.masiv.movies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masiv.movies.models.Theater;
import com.masiv.movies.services.TheaterService;

@RestController
@RequestMapping("/theater")
public class TheaterController {
	@Autowired
	private TheaterService theaterService;
	@PostMapping("/create")
	public ResponseEntity<Theater> createTheater(@RequestBody Theater theater) {
		try {
			Theater createdTheater = theaterService.createTheater(theater);
			return new ResponseEntity<>(createdTheater, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}
