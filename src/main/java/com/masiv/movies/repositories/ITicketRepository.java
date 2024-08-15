package com.masiv.movies.repositories;

import org.springframework.data.repository.CrudRepository;

import com.masiv.movies.models.Ticket;

public interface ITicketRepository extends CrudRepository<Ticket, Long> {}
