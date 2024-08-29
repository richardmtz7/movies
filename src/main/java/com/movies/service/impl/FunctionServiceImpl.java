package com.movies.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.movies.models.Function;
import com.movies.models.Theater;
import com.movies.repositories.IFunctionRepository;
import com.movies.service.FunctionService;
import com.movies.service.TheaterService;
import com.movies.validation.FunctionValidator;

@Service
final public class FunctionServiceImpl implements FunctionService {
	@Autowired
	private IFunctionRepository iFunctionRepository;
	@Autowired
	private TheaterService theaterService;
	@Override
	public Function recordDateFunction(Function function) throws Exception {
		List<Function> listOfFunctions = (List<Function>) iFunctionRepository.findAll();
		for (Function functions : listOfFunctions) {
			if(functions.getAssignedTheater().equals(function.getAssignedTheater()) || 
					functions.getStartDate().isEqual(function.getStartDate())) {
				throw new IllegalArgumentException("There is already a function scheduled in this theater during the specified time");
			}
		}
		
		FunctionValidator.functionValidator(function);
		try {
			return iFunctionRepository.save(function);
		} catch (DataAccessException e) {
			throw new Exception("Error accessing the database while creating function: " + e.getMessage());
		} catch (Exception e) {
			throw new Exception("Error creating function" + e.getMessage());
		}
	}
	@Override
	public void cancelFunction(Function function) throws Exception {
		FunctionValidator.functionIdValidator(function);
		Theater theater = theaterService.getTheaterById(function.getAssignedTheater());
		try {
			function.setStatusFunction(0);
			iFunctionRepository.save(function);
			theaterService.updateAvailableSeats(theater);
		} catch (Exception e) {
			throw new Exception("Error canceling function" + e.getMessage());
		}
	}
	@Override
	public Function getFunctionById(String functionId) throws Exception {
		Function function = iFunctionRepository.findById(functionId)
				.orElseThrow(() -> new NoSuchElementException("Function not found"));
		if (function.getStatusFunction() == 0) {
			throw new NoSuchElementException("The function was cancelled");
		}
		Theater theater = theaterService.getTheaterById(function.getAssignedTheater());
		theaterService.seatAvailables(theater);

		return function;
	}
	@Override
	public List<Function> getFunctions() {

		return (List<Function>) iFunctionRepository.findAll();
	}
	@Override
	public void saveFunction(Function function) {
		iFunctionRepository.save(function);
	}
}
