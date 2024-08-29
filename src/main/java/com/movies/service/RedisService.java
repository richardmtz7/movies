package com.movies.service;

import java.util.concurrent.TimeUnit;

public interface RedisService {
	public boolean acquireLock(String key, long timeout, TimeUnit unit);
	public void releaseLock(String key);
	public void reserveSeats(String reserveId, String functionId);
	public void completePurchase(String reserveId);
}
