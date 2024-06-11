package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.domain.User;
import org.apache.ibatis.annotations.Mapper;


/**
* @author a7249
* @description 针对表【user(用户)】的数据库操作Mapper
* @createDate 2024-06-12 01:20:00
* @Entity generator.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




