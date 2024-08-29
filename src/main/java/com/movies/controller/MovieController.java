package com.movies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movies.models.Movie;
import com.movies.service.MovieService;

@RestController
@RequestMapping("/api/movie")
public class MovieController {
	@Autowired
	private MovieService movieService;
	@PostMapping("/create")
	public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) throws Exception{
		try {
			Movie createdMovie = movieService.createMovie(movie);
			return new ResponseEntity<>(createdMovie, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/all")
	public ResponseEntity<List<Movie>> getAllMovie(){
		try {
			List<Movie> movies = movieService.getAllMovies();
			
			return new ResponseEntity<>(movies, HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}
