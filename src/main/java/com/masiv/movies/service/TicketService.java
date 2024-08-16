package com.masiv.movies.service;

import com.masiv.movies.models.Ticket;

public interface TicketService {
	public Ticket buyTicket(Ticket ticket) throws Exception;
	public void reserveSeats(String reserveId, String functionId, Integer numberOfTickets);
	public void completePurchase(String reserveId);
}
