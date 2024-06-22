package com.augus.redis001_base_operation.redis_template;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;

@Component
@Slf4j
public class RedisTemplate002_List {

    @Resource
    private RedisTemplate redisTemplate;


    public void lpushMultipleExample(String key, String... values) {
        redisTemplate.opsForList().leftPushAll(key, values);
    }


}
