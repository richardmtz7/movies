package com.masiv.movies.controller.test;

import com.masiv.movies.controller.MovieController;
import com.masiv.movies.models.Movie;
import com.masiv.movies.service.impl.MovieServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MovieControllerTest {

    @Mock
    private MovieServiceImpl movieService;

    @InjectMocks
    private MovieController movieController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateMovie_Success() {
        Movie movie = new Movie(1L, "Inception", "Christopher Nolan", "Sci-Fi", 148L, "A mind-bending thriller", 2010, "PG-13");
        when(movieService.createMovie(any(Movie.class))).thenReturn(movie);

        ResponseEntity<Movie> response = movieController.createMovie(movie);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(movie, response.getBody());
    }

    @Test
    public void testCreateMovie_BadRequest() {
        when(movieService.createMovie(any(Movie.class))).thenThrow(IllegalArgumentException.class);

        Movie movie = new Movie();
        ResponseEntity<Movie> response = movieController.createMovie(movie);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
