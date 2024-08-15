package com.masiv.movies.models;

import java.util.Date;

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
	private Long functionId;
	private Long assignedMovie;
	private Long assignedTheater;
	private Date startDate;
	private Date endDate;
}
