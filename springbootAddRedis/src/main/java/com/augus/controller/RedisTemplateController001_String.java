package com.augus.controller;

import com.augus.common.BaseResponse;
import com.augus.common.ResultUtils;
import com.augus.model.dto.RedisStringRequest;
import com.augus.service.RedisTemplateService001_String;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
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
    private RedisTemplateService001_String redisTemplateService001_string;

    @PostMapping("/set")
    public BaseResponse<String> set(@RequestBody RedisStringRequest redisStringRequest, HttpServletRequest request){
        redisTemplateService001_string.setString(redisStringRequest.getKey(), redisStringRequest.getValue());
        return ResultUtils.success(redisStringRequest.getKey());
    }


}
