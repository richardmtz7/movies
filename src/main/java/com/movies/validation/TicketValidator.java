package com.movies.validation;

import com.movies.models.Ticket;

public class TicketValidator {
	public static void validateTicketPurchase(Ticket ticket) {
        if (ticket.getFunctionId() == null) {
            throw new IllegalArgumentException("Function ID cannot be null or not exist");
        }
        if (ticket.getNumberOfTickets() <= 0) {
            throw new IllegalArgumentException("Number of tickets must be greater than zero");
        }
        if (ticket.getBuyerName() == null || ticket.getBuyerName().isEmpty()) {
            throw new IllegalArgumentException("Buyer name cannot be empty");
        }
    }
}
