package com.masiv.movies.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.masiv.movies.controller.TicketController;
import com.masiv.movies.models.Ticket;
import com.masiv.movies.service.impl.TicketServiceImpl;

public class TicketControllerTest {

    @Mock
    private TicketServiceImpl ticketService;

    @InjectMocks
    private TicketController ticketController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPurchaseTicket_Success() {
        Ticket ticket = new Ticket(1L, 2L, "John Doe", "25", 1L, "john.doe@example.com");
        when(ticketService.buyTicket(any(Ticket.class))).thenReturn(ticket);

        ResponseEntity<String> response = ticketController.purchaseTicket(ticket);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Purchase successful.\n Ticket: " + ticket.getPurchaseId().toString(), response.getBody());
    }

    @Test
    public void testPurchaseTicket_BadRequest() {
        when(ticketService.buyTicket(any(Ticket.class))).thenThrow(IllegalArgumentException.class);

        Ticket ticket = new Ticket();
        ResponseEntity<String> response = ticketController.purchaseTicket(ticket);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testPurchaseTicket_ServerError() {
        when(ticketService.buyTicket(any(Ticket.class))).thenThrow(RuntimeException.class);

        Ticket ticket = new Ticket();
        ResponseEntity<String> response = ticketController.purchaseTicket(ticket);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
