package com.augus.controller;

import com.augus.common.BaseResponse;
import com.augus.common.ResultUtils;
import com.augus.model.dto.RedisStringExRequest;
import com.augus.model.dto.RedisStringRequest;
import com.augus.service.imp.RedisTemplateServiceImp001_String;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestController
@RequestMapping("/redisTemplate/string")
public class RedisTemplateController001_String {

    @Resource
    private RedisTemplateServiceImp001_String redisTemplateService001_string;

    @PostMapping("/set")
    public BaseResponse<String> set(@RequestBody RedisStringRequest redisStringRequest, HttpServletRequest request){
        redisTemplateService001_string.setString(redisStringRequest.getKey(), redisStringRequest.getValue());
        return ResultUtils.success(redisStringRequest.getKey());
    }


    @PostMapping("/setnx")
    public BaseResponse<Boolean> setnx(@RequestBody  RedisStringRequest redisStringRequest, HttpServletRequest request){
        boolean result = redisTemplateService001_string.setnxString(redisStringRequest.getKey(), redisStringRequest.getValue());
        return ResultUtils.success(result);
    }

    @PostMapping("/setex")
    public BaseResponse<String> setex(@RequestBody RedisStringExRequest redisStringExRequest, HttpServletRequest request){
        redisTemplateService001_string.setexString(redisStringExRequest.getKey(), redisStringExRequest.getValue(), redisStringExRequest.getExpireTime());
        return ResultUtils.success(redisStringExRequest.getKey());
    }




}
