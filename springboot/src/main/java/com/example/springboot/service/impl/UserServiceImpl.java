package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.domain.User;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.UserService;

import org.springframework.stereotype.Service;

/**
* @author a7249
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2024-06-12 01:20:00
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

}




