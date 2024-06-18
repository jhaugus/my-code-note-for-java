package com.augus.redis001_base_operation.redis_template;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class RedisTemplate001_String {

    // 1. 获取RedisTemplate对象
    @Resource
    private RedisTemplate redisTemplate;


    public void setString(String key, String value){
        // 2. 执行set方法
        redisTemplate.opsForValue().set(key,value);
        log.info("RedisTemplateString获取 key = " + key + " value = " + redisTemplate.opsForValue().get(key));
    }

    public void setnxString(String key, String value){
        boolean wasSet =  redisTemplate.opsForValue().setIfAbsent("setnxString", "setnxString_value");
        if (wasSet) {
            log.info("Key was set to " + key);
        } else {
            log.info("Key already exists");
        }
    }
}
