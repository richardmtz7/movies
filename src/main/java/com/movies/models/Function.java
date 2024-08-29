package com.movies.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@RedisHash("Function")
public class Function {
	@Id
	private String id;
	private String assignedMovie;
	private String assignedTheater;
	private LocalDateTime  startDate;
	private LocalDateTime  endDate;
	private Integer statusFunction = 1;
	private Integer ticketsSold = 0;
}
