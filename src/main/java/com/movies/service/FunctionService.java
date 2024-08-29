package com.movies.service;

import java.util.List;

import com.movies.models.Function;

public interface FunctionService {
	public Function recordDateFunction(Function function) throws Exception;
	public void cancelFunction(Function function) throws Exception;
	public Function getFunctionById(String functionId) throws Exception;
	public List<Function> getFunctions();
	public void saveFunction(Function function);
}
