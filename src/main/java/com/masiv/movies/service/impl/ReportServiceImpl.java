package com.masiv.movies.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masiv.movies.models.Function;
import com.masiv.movies.service.FunctionService;
import com.masiv.movies.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService{
	@Autowired
	private FunctionService functionService;
	@Override
	public List<Function> getTopFunctionByTicketsSold(Long top) {
		List<Function> functions = functionService.getFunctions();
        
		return getTopNByTicketsSold(functions, top);
	}
	private List<Function> getTopNByTicketsSold(List<Function> functions, Long top) {
        functions.sort((f1, f2) -> f2.getTicketsSold().compareTo(f1.getTicketsSold()));

        return functions.stream().limit(top).collect(Collectors.toList());
    }

}
