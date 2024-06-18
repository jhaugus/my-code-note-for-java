package com.augus.redis001_base_operation.jedis;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

@Slf4j
public class RedisDataStructure002_List {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);
        log.info("redis conn status:{}","连接成功");
        log.info("redis ping retvalue:{}",jedis.ping());

        jedis.lpush("listtest", "value1", "value2", "value3");
        log.info("listtest values:{}",jedis.lrange("listtest", 0, -1));

//        log.info("listtest values:{}",jedis.rpop("listtest"));


        jedis.close();
    }
}
