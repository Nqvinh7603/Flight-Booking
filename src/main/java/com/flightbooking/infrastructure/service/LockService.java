package com.flightbooking.infrastructure.service;

import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class LockService {
    private final RedissonClient redissonClient;

    @Autowired
    public LockService(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public boolean acquireLock(String lockKey, long expirationTime) {
        // Lua script for acquiring a lock with a set expiration time
        // Ensure the expiration time is passed as an integer value in milliseconds
        String script = "if redis.call('set', KEYS[1], ARGV[1], 'NX', 'PX', ARGV[2]) then return 'OK' else return 'FAIL' end";
        Object result = redissonClient.getScript(StringCodec.INSTANCE).eval(
                RScript.Mode.READ_WRITE,
                script,
                RScript.ReturnType.VALUE,
                Collections.singletonList(lockKey),
                "lockValue", // Assuming "lockValue" is the value you want to set for the lock
                expirationTime // Pass the expiration time directly as a long value
        );
        return "OK".equals(result);
    }

    public void releaseLock(String lockKey) {
        redissonClient.getBucket(lockKey, StringCodec.INSTANCE).delete();
    }
}