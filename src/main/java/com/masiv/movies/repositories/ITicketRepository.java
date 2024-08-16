package com.masiv.movies.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.masiv.movies.models.Ticket;

@Repository
public interface ITicketRepository extends CrudRepository<Ticket, String> {}
