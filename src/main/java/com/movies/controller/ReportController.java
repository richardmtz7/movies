package com.movies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movies.models.Function;
import com.movies.service.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
	@Autowired
	private ReportService reportService;
	@GetMapping("/top")
	public List<Function> getTopFunction(@RequestParam(defaultValue = "10") Integer top){
		
		return reportService.getTopFunctionByTicketsSold(top);
	}
}
