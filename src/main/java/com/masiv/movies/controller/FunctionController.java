package com.masiv.movies.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masiv.movies.models.Function;
import com.masiv.movies.service.FunctionService;

@RestController
@RequestMapping("/api/function")
public class FunctionController {
	@Autowired
	private FunctionService functionServiceImpl;
	@PostMapping("/create")
	public ResponseEntity<Function> createFunction(@RequestBody Function function) throws Exception{
		try {
			Function createFunction = functionServiceImpl.recordDateFunction(function);
			
			return new ResponseEntity<>(createFunction, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	@GetMapping("get/{id}")
	public ResponseEntity<Function> getFunctionBydId(@PathVariable String id) throws Exception{
		try { 
	        Function function = functionServiceImpl.getFunctionById(id);
	        if (function == null) {
	            throw new NoSuchElementException();
	        }
	        if(function.getStatusFunction() == 0) {
	        	throw new NoSuchElementException();
	        }
	        return new ResponseEntity<>(function, HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	    	throw new NoSuchElementException(e.getMessage());
	    }
	}
	@DeleteMapping("/cancel")
    public ResponseEntity<Void> cancelFunction(@RequestBody Function function) throws Exception {
        try {
            functionServiceImpl.cancelFunction(function);
            
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
        	throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
        	throw new Exception(e.getMessage());
        }
    }
	@GetMapping("get/all")
	public ResponseEntity<List<Function>> getAllFunctions() throws Exception{
		try {
            List<Function> functions = functionServiceImpl.getFunctions();
            return new ResponseEntity<>(functions, HttpStatus.OK);
        } catch (Exception e) {
        	throw new Exception(e.getMessage());
        }
	}
}
