package com.masiv.movies.controller;

import java.util.List;

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
@RequestMapping("/function")
public class FunctionController {
	@Autowired
	private FunctionService functionServiceImpl;
	@PostMapping("/create")
	public ResponseEntity<Function> createFunction(@RequestBody Function function){
		try {
			Function createFunction = functionServiceImpl.recordDateFunction(function);
			
			return new ResponseEntity<>(createFunction, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("get/{id}")
	public ResponseEntity<Function> getFunctionBydId(@PathVariable Long id){
		try { 
	        Function function = functionServiceImpl.getFunctionById(id);
	        if (function == null) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<>(function, HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	@DeleteMapping("/cancel")
    public ResponseEntity<Void> cancelFunction(@RequestBody Function function) {
        try {
            functionServiceImpl.cancelFunction(function);
            
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	@GetMapping("get/all")
	public ResponseEntity<List<Function>> getAllFunctions(){
		try {
            List<Function> functions = functionServiceImpl.getFunctions();
            return new ResponseEntity<>(functions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
}
