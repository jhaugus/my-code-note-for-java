package com.augus.service;


import com.augus.redis001_base_operation.redis_template.RedisTemplate001_String;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RedisTemplateService001_String {
    @Resource
    private RedisTemplate001_String redisTemplate001String;

    public void setString(String key, String value){
        redisTemplate001String.setString(key, value);
    }


}
