package com.movies.controller.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.movies.controller.MovieController;
import com.movies.models.Movie;
import com.movies.service.impl.MovieServiceImpl;

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
    public void testCreateMovie_Success() throws Exception {
        Movie movie = new Movie("1", "Inception", "Christopher Nolan", "Sci-Fi", 148, "A mind-bending thriller", 2010, "PG-13");
        when(movieService.createMovie(any(Movie.class))).thenReturn(movie);

        ResponseEntity<Movie> response = movieController.createMovie(movie);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(movie, response.getBody());
    }

    @Test
    public void testCreateMovie_BadRequest() throws Exception {
        when(movieService.createMovie(any(Movie.class))).thenThrow(IllegalArgumentException.class);

        Movie movie = new Movie();
        ResponseEntity<Movie> response = movieController.createMovie(movie);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
