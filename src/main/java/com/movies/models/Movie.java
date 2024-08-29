package com.movies.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@RedisHash("Movie")
public class Movie {
	@Id
	private String id;
	private String title;
	private String director;
	private String genre;
	private Integer duration;
	private String synopsis;
	private Integer releaseYear;
	private String ageRating;
}
