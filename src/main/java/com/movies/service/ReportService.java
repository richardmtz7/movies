package com.movies.service;

import java.util.List;

import com.movies.models.Function;

public interface ReportService {
	public List<Function> getTopFunctionByTicketsSold(Integer top);
}
