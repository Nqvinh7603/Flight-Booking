package com.flightbooking.infrastructure.service.impl;

import com.flightbooking.infrastructure.service.LockService;
import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class LockServiceImpl implements LockService {
    private final RedissonClient redissonClient;

    @Autowired
    public LockServiceImpl(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public boolean acquireLock(String lockKey, long expirationTime) {
        String script = "if redis.call('set', KEYS[1], ARGV[1], 'NX', 'PX', ARGV[2]) then return 'OK' else return 'FAIL' end";
        Object result = redissonClient.getScript(StringCodec.INSTANCE).eval(
                RScript.Mode.READ_WRITE,
                script,
                RScript.ReturnType.VALUE,
                Collections.singletonList(lockKey),
                "lockValue",
                expirationTime
        );
        return "OK".equals(result);
    }

    @Override
    public void releaseLock(String lockKey) {
        redissonClient.getBucket(lockKey, StringCodec.INSTANCE).delete();
    }
}