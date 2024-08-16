package com.masiv.movies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masiv.movies.models.Ticket;
import com.masiv.movies.service.TicketService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ticket")
public class TicketController {
	@Autowired
	private TicketService ticketService;
	@PostMapping("/purchase")
    public ResponseEntity<String> purchaseTicket(@Valid @RequestBody Ticket ticket) throws Exception {
        try {
            Ticket created = ticketService.buyTicket(ticket);
            
            return new ResponseEntity<>("Purchase successful.\n Ticket: " + created.getId(), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}