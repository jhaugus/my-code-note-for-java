package com.augus;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @auther zzyy
 * @create 2022-12-10 23:39
 */
@SpringBootApplication
@MapperScan("com.augus.mapper") //import tk.mybatis.spring.annotation.MapperScan;
public class RedisApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(RedisApplication.class,args);
    }
}
