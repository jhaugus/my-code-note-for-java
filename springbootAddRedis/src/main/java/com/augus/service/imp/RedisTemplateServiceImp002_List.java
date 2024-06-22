package com.augus.service.imp;


import com.augus.redis001_base_operation.redis_template.RedisTemplate002_List;
import com.augus.service.RedisTemplateService002_List;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RedisTemplateServiceImp002_List implements RedisTemplateService002_List {

    @Resource
    private RedisTemplate002_List redisTemplate002List;

    @Override
    public void lpushMultipleExample(String key, String... values) {
        redisTemplate002List.lpushMultipleExample(key, values);
    }
}
