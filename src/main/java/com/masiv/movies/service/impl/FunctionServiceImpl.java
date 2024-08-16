package com.masiv.movies.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.masiv.movies.models.Function;
import com.masiv.movies.models.Theater;
import com.masiv.movies.repositories.IFunctionRepository;
import com.masiv.movies.service.FunctionService;
import com.masiv.movies.service.TheaterService;
import com.masiv.movies.validation.FunctionValidator;

@Service
final public class FunctionServiceImpl implements FunctionService {
	@Autowired
	private IFunctionRepository iFunctionRepository;
	@Autowired
	private TheaterService theaterService;
	@Override
	public Function recordDateFunction(Function function) throws Exception {
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
}
