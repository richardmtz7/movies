package com.movies.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.movies.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {
	@Autowired
	private StringRedisTemplate redisTemplate;

	@Override
	public boolean acquireLock(String key, long timeout, TimeUnit unit) {
		return redisTemplate.opsForValue().setIfAbsent(key, "locked", timeout, unit);
	}

	@Override
	public void releaseLock(String key) {
		redisTemplate.delete(key);
	}

	@Override
	public void reserveSeats(String reserveId, String functionId) {
		String reservationKey = "reservation:" + reserveId;
        redisTemplate.opsForValue().set(reservationKey, functionId, 15, TimeUnit.MINUTES);
	}

	@Override
	public void completePurchase(String reserveId) {
		String reservationKey = "reservation:" + reserveId;
        redisTemplate.delete(reservationKey);
	}
	
}
