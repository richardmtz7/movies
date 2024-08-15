package com.masiv.movies.controller.test;

import com.masiv.movies.controller.TheaterController;
import com.masiv.movies.models.Theater;
import com.masiv.movies.service.impl.TheaterServiceImpl;

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

public class TheaterControllerTest {

    @Mock
    private TheaterServiceImpl theaterService;

    @InjectMocks
    private TheaterController theaterController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTheater_Success() {
        Theater theater = new Theater(1L, "Theater 1", 1L, 100L, 100L, "Description");
        when(theaterService.createTheater(any(Theater.class))).thenReturn(theater);

        ResponseEntity<Theater> response = theaterController.createTheater(theater);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(theater, response.getBody());
    }

    @Test
    public void testCreateTheater_BadRequest() {
        when(theaterService.createTheater(any(Theater.class))).thenThrow(IllegalArgumentException.class);

        Theater theater = new Theater();
        ResponseEntity<Theater> response = theaterController.createTheater(theater);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
