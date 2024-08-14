package com.masiv.movies.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@RedisHash("Movie")
public class Movie {
	@Id
	private Long id;
	private String title;
	private String director;
	private String gender;
	private Long duration;
	private String synopsis;
	private Integer releaseYear;
	private String ageRating;
}
