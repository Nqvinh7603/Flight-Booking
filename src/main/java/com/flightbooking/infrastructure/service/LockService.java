package com.flightbooking.infrastructure.service;

public interface LockService {
    boolean acquireLock(String lockKey, long expirationTime);
    void releaseLock(String lockKey);
}