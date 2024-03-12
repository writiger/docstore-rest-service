package com.ds.user.redis;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * @author writiger
 * @description
 * @create_at 2024-03-11 15:16
 */
@ActiveProfiles("test")
@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void setStringRedisTemplate(){
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("code_name3", "code_redis",40, TimeUnit.SECONDS);
        ops.set("name", "return_name");
    }

    @Test
    void getStringRedisTemplate() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        System.out.println(ops.get("name"));
        // 输出为：null
        System.out.println(ops.get("code_name3"));
        // 输出为：code_redis
    }

    @Test
    void setRedisTemplate() {
        HashOperations ops = redisTemplate.opsForHash();
        ops.put("code_info", "code_name", "code_info_redis");

    }

    @Test
    void getRedisTemplate() {
        HashOperations ops = redisTemplate.opsForHash();
        System.out.println(ops.get("info", "name"));
        // 输出为：null
        System.out.println(ops.get("code_info", "code_name"));
        // 输出为：code_info_redis
    }
}
