package com.movies.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
	@NotNull
	@NotEmpty
	private String buyerName;
	private String buyerAge;
	private String functionId;
	@Email(message = "Email should be valid")
	private String buyerEmail;
}
