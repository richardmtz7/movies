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
	private Long theaterId;
	private String theaterName;
	private Long cinemaId;
	private Integer totalSeatingCapacity;
	private String description; 
}
