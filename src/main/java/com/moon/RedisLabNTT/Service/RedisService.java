package com.moon.RedisLabNTT.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.moon.RedisLabNTT.Entity.Parametro;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Parametro> redisTemplate;

    public void put(String key, Parametro value) {
        redisTemplate.opsForValue().set(key, value, 30, TimeUnit.MINUTES);
    }

    public Parametro get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
    
    public Set<String> getAllParametros() {
        String pattern = "map-parametros:*";
        return redisTemplate.keys(pattern);
    }
}