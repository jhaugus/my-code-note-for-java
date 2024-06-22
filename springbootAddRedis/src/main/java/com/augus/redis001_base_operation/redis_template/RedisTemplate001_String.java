package com.augus.redis001_base_operation.redis_template;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisTemplate001_String {

    // 1. 获取RedisTemplate对象
    @Resource
    private RedisTemplate redisTemplate;


    /**
     * 向redis中插入key-value，如果有key，则覆盖
     */
    public void setString(String key, String value){
        // 2. 执行set方法
        redisTemplate.opsForValue().set(key,value);
        log.info("RedisTemplateString获取 key = " + key + " value = " + redisTemplate.opsForValue().get(key));
    }

    /**
     * 如果redis中没有key，则将key-value插入redis，返回 true
     * 如果redis中有key，则不插入，返回 false
     */
    public boolean setnxString(String key, String value){
        boolean wasSet =  redisTemplate.opsForValue().setIfAbsent(key, value);
        if (wasSet) {
            log.info("Key was set to " + key);
        } else {
            log.info("Key already exists");
        }
        return wasSet;
    }

    public void setexString(String key, String value, long second){
        redisTemplate.opsForValue().set(key, value, second, TimeUnit.SECONDS);
    }



}
