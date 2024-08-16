package com.masiv.movies.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@RedisHash("Ticket")
public class Ticket {
	@Id
	private String id; 
	private Integer numberOfTickets;
	private String buyerName;
	private String buyerAge;
	private String functionId;
	private String buyerEmail;
}
