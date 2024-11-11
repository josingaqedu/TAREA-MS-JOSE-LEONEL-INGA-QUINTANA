package com.ms_open_feign.ms_open_feign.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    private final StringRedisTemplate redisTemplate;

    public RedisService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setData(String key, String value, int exp){
        redisTemplate.opsForValue().set(key,value);
        redisTemplate.expire(key, exp, TimeUnit.MINUTES);
    }

    public String getData(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public void deleteData(String key){
        redisTemplate.delete(key);
    }
}
