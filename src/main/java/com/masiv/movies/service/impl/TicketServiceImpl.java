package com.masiv.movies.service.impl;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.masiv.movies.models.Function;
import com.masiv.movies.models.Theater;
import com.masiv.movies.models.Ticket;
import com.masiv.movies.repositories.ITicketRepository;
import com.masiv.movies.service.FunctionService;
import com.masiv.movies.service.TheaterService;
import com.masiv.movies.service.TicketService;
import com.masiv.movies.validation.TheaterValidator;
import com.masiv.movies.validation.TicketValidator;

@Service
final public class TicketServiceImpl implements TicketService{
	@Autowired
	private ITicketRepository iTicketRepository;
	@Autowired
	private FunctionService functionService;
	@Autowired
	private TheaterService theaterService;
	@Autowired
    private StringRedisTemplate redisTemplate;
	@Override
	public Ticket buyTicket(Ticket ticket) throws Exception {
		String generationReserveId = UUID.randomUUID().toString();
		reserveSeats(generationReserveId, ticket.getFunctionId(), ticket.getNumberOfTickets());
		String lockKey = "lock:function:" + ticket.getFunctionId();
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        boolean acquired = operations.setIfAbsent(lockKey, "locked", 10, TimeUnit.SECONDS);
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
            functionService.recordDateFunction(function);
            theaterService.updateAvailableSeats(theater);
            completePurchase(generationReserveId);
            return ticket;
        } finally {
            redisTemplate.delete(lockKey);
        }
	}
	@Override
	public void reserveSeats(String reserveId, Long functionId, Long numberOfTickets) {
		String reservationKey = "reservation:" + reserveId;
	    redisTemplate.opsForValue().set(reservationKey, functionId.toString(), 15, TimeUnit.MINUTES);
	}
	@Override
	public void completePurchase(String reserveId) {
		String reservationKey = "reservation:" + reserveId;
        redisTemplate.delete(reservationKey);
	}
}
