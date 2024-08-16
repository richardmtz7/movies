package com.masiv.movies.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@RedisHash("Theater")
public class Theater {
	@Id
	private String id;
	private String theaterName;
	private String cinemaId;
	private Integer availableSeats;
	private Integer totalSeatingCapacity;
	private String description;
}
