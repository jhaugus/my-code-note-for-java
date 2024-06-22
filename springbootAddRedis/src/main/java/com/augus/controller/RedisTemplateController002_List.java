package com.augus.controller;


import com.augus.common.BaseResponse;
import com.augus.common.ResultUtils;
import com.augus.service.imp.RedisTemplateServiceImp002_List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/redisTemplate/list")
@Api(tags = "List")
public class RedisTemplateController002_List {

    @Resource
    private RedisTemplateServiceImp002_List redisTemplateServiceImp002List;

    @PostMapping("/lpush")
    @ApiOperation("lpush")
    public BaseResponse<String> lpush(String key,String ...values){
        redisTemplateServiceImp002List.lpushMultipleExample(key,values);
        return ResultUtils.success(key);
    }

}
