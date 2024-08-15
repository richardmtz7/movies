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
	private Long purchaseId; 
	private Long numberOfTickets;
	private String buyerName;
	private String buyerAge;
	private Long functionId;
	private String buyerEmail;
}
