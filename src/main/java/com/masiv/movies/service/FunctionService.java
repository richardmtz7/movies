package com.masiv.movies.service;

import java.util.List;

import com.masiv.movies.models.Function;

public interface FunctionService {
	public Function recordDateFunction(Function function);
	public void cancelFunction(Function function) throws Exception;
	public Function getFunctionById(Long functionId) throws Exception;
	public List<Function> getFunctions();
}
