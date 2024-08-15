package com.masiv.movies.service;

import java.util.List;

import com.masiv.movies.models.Function;

public interface ReportService {
	public List<Function> getTopFunctionByTicketsSold(Long top);
}
