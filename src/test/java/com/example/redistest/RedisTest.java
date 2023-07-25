package com.example.redistest;

import com.example.toyProject.config.redis.RedisConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest(classes = RedisConfig.class)
public class RedisTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void testString() {
        final String key = "something";

        final ValueOperations<String, String> stringValueOperations = redisTemplate.opsForValue();

        stringValueOperations.set(key, "1");

        final String resultOne = stringValueOperations.get(key);

        log.info("message = {}, key = {}", resultOne, key);

        stringValueOperations.increment(key);

        final String resultTwo = stringValueOperations.get(key);

        log.info("message = {}", resultTwo);
    }
}