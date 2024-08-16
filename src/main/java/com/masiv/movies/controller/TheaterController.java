package com.masiv.movies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masiv.movies.models.Theater;
import com.masiv.movies.service.TheaterService;

@RestController
@RequestMapping("/api/theater")
public class TheaterController {
	@Autowired
	private TheaterService theaterService;
	@PostMapping("/create")
	public ResponseEntity<Theater> createTheater(@RequestBody Theater theater) throws Exception {
		try {
			Theater createdTheater = theaterService.createTheater(theater);
			return new ResponseEntity<>(createdTheater, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/get/{id}")
	public ResponseEntity<Theater> getTheaterById(@PathVariable String id) throws Exception {
		try {
			Theater theater = theaterService.getTheaterById(id);
			return new ResponseEntity<>(theater, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("get/all")
	public ResponseEntity<List<Theater>> getAllFunctions() throws Exception{
		try {
            List<Theater> theaters = theaterService.getAllTheaters();
            return new ResponseEntity<>(theaters, HttpStatus.OK);
        } catch (Exception e) {
        	throw new Exception(e.getMessage());
        }
	}
}
