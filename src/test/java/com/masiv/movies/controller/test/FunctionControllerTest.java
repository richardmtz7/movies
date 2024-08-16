package com.masiv.movies.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.masiv.movies.models.Function;
import com.masiv.movies.models.Theater;
import com.masiv.movies.repositories.IFunctionRepository;
import com.masiv.movies.service.impl.FunctionServiceImpl;
import com.masiv.movies.service.impl.TheaterServiceImpl;

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
        Function function = new Function("1", "1", "1", new Date(), new Date(), 100);
        when(functionRepository.save(any(Function.class))).thenReturn(function);
        Function result = functionService.recordDateFunction(function);
        assertNotNull(result);
        assertEquals("1", result.getAssignedMovie());
        verify(functionRepository, times(1)).save(function);
    }
    @Test
    public void testCancelFunction_ValidFunction() throws Exception {
    	Function function = new Function("1", "1", "1", new Date(), new Date(), 100);
    	Theater theater = new Theater("1", "1", "1", 0, 0, "Test description");
        when(theaterService.getTheaterById(any())).thenReturn(theater);
        functionService.cancelFunction(function);
        verify(theaterService, times(1)).updateAvailableSeats(theater);
        verify(functionRepository, times(1)).deleteById(function.getId());
    }
    @Test
    public void testGetFunctionById_FunctionExists() throws Exception {
        Function function = new Function("1", "1", "1", new Date(), new Date(), 100);
        Theater theater = new Theater("1", "1", "1", 0, 0, "Test description");
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
        Exception exception = assertThrows(Exception.class, () -> {
            functionService.getFunctionById("1");
        });
        assertEquals("Function not found", exception.getMessage());
        verify(functionRepository, times(1)).findById("1");
    }
    @Test
    public void testGetFunctions() {
        List<Function> functions = new ArrayList<>();
        functions.add(new Function("1", "1", "1", new Date(), new Date(), 100));
        when(functionRepository.findAll()).thenReturn(functions);
        List<Function> result = functionService.getFunctions();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(functionRepository, times(1)).findAll();
    }
}
