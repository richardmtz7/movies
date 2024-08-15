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
    public void testRecordDateFunction_ValidFunction() {
        Function function = new Function(1L, 1L, 1L, new Date(), new Date());
        when(functionRepository.save(any(Function.class))).thenReturn(function);
        Function result = functionService.recordDateFunction(function);
        assertNotNull(result);
        assertEquals(1, result.getAssignedMovie());
        verify(functionRepository, times(1)).save(function);
    }
    @Test
    public void testCancelFunction_ValidFunction() throws Exception {
    	Function function = new Function(1L, 1L, 1L, new Date(), new Date());
    	Theater theater = new Theater(1L, "Theater 1", 1L, 100L, 100L, "Description");
        when(theaterService.getTheaterById(anyLong())).thenReturn(theater);
        functionService.cancelFunction(function);
        verify(theaterService, times(1)).updateAvailableSeats(theater);
        verify(functionRepository, times(1)).deleteById(function.getFunctionId());
    }
    @Test
    public void testGetFunctionById_FunctionExists() throws Exception {
        Function function = new Function(1L, 1L, 1L, new Date(), new Date());
        Theater theater = new Theater(1L, "Theater 1", 1L, 100L, 100L, "Description");
        when(functionRepository.findById(anyLong())).thenReturn(Optional.of(function));
        when(theaterService.getTheaterById(anyLong())).thenReturn(theater);
        Function result = functionService.getFunctionById(1L);
        assertNotNull(result);
        assertEquals(1, result.getAssignedMovie());
        verify(functionRepository, times(1)).findById(1L);
    }
    @Test
    public void testGetFunctionById_FunctionNotFound() {
        when(functionRepository.findById(anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(Exception.class, () -> {
            functionService.getFunctionById(1L);
        });
        assertEquals("Function not found", exception.getMessage());
        verify(functionRepository, times(1)).findById(1L);
    }
    @Test
    public void testGetFunctions() {
        List<Function> functions = new ArrayList<>();
        functions.add(new Function(1L, 1L, 1L, new Date(), new Date()));
        when(functionRepository.findAll()).thenReturn(functions);
        List<Function> result = functionService.getFunctions();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(functionRepository, times(1)).findAll();
    }
}
