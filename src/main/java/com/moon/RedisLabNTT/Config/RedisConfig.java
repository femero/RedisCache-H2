package com.moon.RedisLabNTT.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moon.RedisLabNTT.Entity.Parametro;

@Configuration
public class RedisConfig {

	@SuppressWarnings("removal")
	@Bean
    RedisTemplate<String, Parametro> redisTemplate(RedisConnectionFactory connectionFactory, ObjectMapper objectMapper) {

        RedisTemplate<String, Parametro> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        Jackson2JsonRedisSerializer<Parametro> serializer = new Jackson2JsonRedisSerializer<>(Parametro.class);
        serializer.setObjectMapper(objectMapper);

        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        return template;
    }
}