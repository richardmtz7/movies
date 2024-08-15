package com.masiv.movies.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masiv.movies.models.Function;
import com.masiv.movies.models.Theater;
import com.masiv.movies.repositories.IFunctionRepository;
import com.masiv.movies.service.FunctionService;
import com.masiv.movies.service.TheaterService;
import com.masiv.movies.validation.FunctionValidator;

@Service
final public class FunctionServiceImpl implements FunctionService{
	@Autowired
	private IFunctionRepository iFunctionRepository;
	@Autowired
    private TheaterService theaterService;
	@Override
	public Function recordDateFunction(Function function) {
		FunctionValidator.functionValidator(function);
		
		return iFunctionRepository.save(function);
	}
	@Override
	public void cancelFunction(Function function) throws Exception {
		FunctionValidator.functionIdValidator(function);
		Theater theater = theaterService.getTheaterById(function.getAssignedTheater());
		theaterService.updateAvailableSeats(theater);
		iFunctionRepository.deleteById(function.getFunctionId());
	}
	@Override
	public Function getFunctionById(Long functionId) throws Exception {
		Function function = iFunctionRepository.findById(functionId)
				.orElseThrow(() -> new Exception("Function not found"));
		Theater theater = theaterService.getTheaterById(function.getAssignedTheater());
		theaterService.seatAvailables(theater);
		
		return function;
	}
	@Override
	public List<Function> getFunctions() {
		
		return (List<Function>) iFunctionRepository.findAll();
	}
}
