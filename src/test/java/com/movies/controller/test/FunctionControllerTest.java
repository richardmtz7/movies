package com.movies.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.movies.models.Function;
import com.movies.models.Theater;
import com.movies.repositories.IFunctionRepository;
import com.movies.service.impl.FunctionServiceImpl;
import com.movies.service.impl.TheaterServiceImpl;

public class FunctionControllerTest {

	@Mock
    private IFunctionRepository functionRepository;

    @Mock
    private TheaterServiceImpl theaterService;

    @InjectMocks
    private FunctionServiceImpl functionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRecordDateFunction_ValidFunction() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        Function function = new Function("1", "1", "1", now, now.plusHours(2), 1, 0);
        when(functionRepository.save(any(Function.class))).thenReturn(function);
        Function result = functionService.recordDateFunction(function);
        assertNotNull(result);
        assertEquals("1", result.getAssignedMovie());
        verify(functionRepository, times(1)).save(function);
    }

    @Test
    public void testCancelFunction_ValidFunction() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        Function function = new Function("1", "1", "1", now, now.plusHours(2), 1, 0);
        Theater theater = new Theater("1", "1", "1", 10, 10, "Test description");
        when(theaterService.getTheaterById(any())).thenReturn(theater);
        when(functionRepository.save(any(Function.class))).thenReturn(function);
        functionService.cancelFunction(function);
        verify(theaterService, times(1)).updateAvailableSeats(theater);
        verify(functionRepository, times(1)).save(function);
    }

    @Test
    public void testGetFunctionById_FunctionExists() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        Function function = new Function("1", "1", "1", now, now.plusHours(2), 1, 0);
        Theater theater = new Theater("1", "1", "1", 10, 10, "Test description");
        when(functionRepository.findById(any())).thenReturn(Optional.of(function));
        when(theaterService.getTheaterById(any())).thenReturn(theater);
        Function result = functionService.getFunctionById("1");
        assertNotNull(result);
        assertEquals("1", result.getAssignedMovie());
        verify(functionRepository, times(1)).findById("1");
    }

    @Test
    public void testGetFunctionById_FunctionNotFound() {
        when(functionRepository.findById(any())).thenReturn(Optional.empty());
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            functionService.getFunctionById("1");
        });
        assertEquals("Function not found", exception.getMessage());
        verify(functionRepository, times(1)).findById("1");
    }

    @Test
    public void testGetFunctionById_FunctionCancelled() {
        LocalDateTime now = LocalDateTime.now();
        Function function = new Function("1", "1", "1", now, now.plusHours(2), 0, 0);
        when(functionRepository.findById(any())).thenReturn(Optional.of(function));
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            functionService.getFunctionById("1");
        });
        assertEquals("The function was cancelled", exception.getMessage());
        verify(functionRepository, times(1)).findById("1");
    }

    @Test
    public void testGetFunctions() {
        LocalDateTime now = LocalDateTime.now();
        List<Function> functions = new ArrayList<>();
        functions.add(new Function("1", "1", "1", now, now.plusHours(2), 1, 0));
        when(functionRepository.findAll()).thenReturn(functions);
        List<Function> result = functionService.getFunctions();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(functionRepository, times(1)).findAll();
    }
}
