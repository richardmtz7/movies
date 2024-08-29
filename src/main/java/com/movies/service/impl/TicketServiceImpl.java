package com.movies.service.impl;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movies.models.Function;
import com.movies.models.Theater;
import com.movies.models.Ticket;
import com.movies.repositories.ITicketRepository;
import com.movies.service.FunctionService;
import com.movies.service.RedisService;
import com.movies.service.TheaterService;
import com.movies.service.TicketService;
import com.movies.validation.TheaterValidator;
import com.movies.validation.TicketValidator;

@Service
final public class TicketServiceImpl implements TicketService{
	@Autowired
	private ITicketRepository iTicketRepository;
	@Autowired
	private FunctionService functionService;
	@Autowired
	private TheaterService theaterService;
	@Autowired
	private RedisService redisService;
	@Override
	public Ticket buyTicket(Ticket ticket) throws Exception {
		String generationReserveId = UUID.randomUUID().toString();
        redisService.reserveSeats(generationReserveId, ticket.getFunctionId());

        String lockKey = "lock:function:" + ticket.getFunctionId();
        boolean acquired = redisService.acquireLock(lockKey, 10, TimeUnit.SECONDS);
        if (!acquired) {
            throw new IllegalArgumentException("Another user is currently purchasing tickets");
        }
        try {
        	TicketValidator.validateTicketPurchase(ticket);
        	Function function = functionService.getFunctionById(ticket.getFunctionId());
        	function.setTicketsSold(function.getTicketsSold() + ticket.getNumberOfTickets());
        	Theater theater = theaterService.getTheaterById(function.getAssignedTheater());
            if (theater.getAvailableSeats() < ticket.getNumberOfTickets()) {
                throw new IllegalArgumentException("Not enough available seats");
            }
            iTicketRepository.save(ticket);
            TheaterValidator.theaterValidator(theater);
            theater.setAvailableSeats(theater.getAvailableSeats() - ticket.getNumberOfTickets());
            functionService.saveFunction(function);
            theaterService.updateAvailableSeats(theater);
            redisService.completePurchase(generationReserveId);
            return ticket;
        } finally {
            redisService.releaseLock(lockKey);
        }
	}
}
