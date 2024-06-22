package com.augus.service.imp;


import com.augus.redis001_base_operation.redis_template.RedisTemplate001_String;
import com.augus.service.RedisTemplateService001_String;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RedisTemplateServiceImp001_String implements RedisTemplateService001_String {
    @Resource
    private RedisTemplate001_String redisTemplate001String;

    public void setString(String key, String value){
        redisTemplate001String.setString(key, value);
    }

    @Override
    public boolean setnxString(String key, String value) {
        boolean result = redisTemplate001String.setnxString(key, value);
        return result;
    }

    @Override
    public void setexString(String key, String value, long expireTime) {
        redisTemplate001String.setexString(key, value, expireTime);
    }


}
