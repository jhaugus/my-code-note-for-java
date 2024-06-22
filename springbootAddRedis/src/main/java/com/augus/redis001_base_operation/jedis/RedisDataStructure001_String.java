package com.augus.redis001_base_operation.jedis;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

@Slf4j
public class RedisDataStructure001_String {


    public static void main(String[] args) throws InterruptedException {
        Jedis jedis = new Jedis("localhost", 6379);


        // 1.增加string k v
        jedis.set("k1", "v1");
        jedis.setnx("k1", "v2");  // 如果存在k1，则不改变value


        // 2.设置 k2:v2 过期时间为10s
        jedis.setex("k2", 10, "v2");
        Thread.sleep(2000);



        long k2 = jedis.ttl("k2");
        System.out.println("经过两秒的时间 k2'ttl = " + k2 + "s");


        // 3. 设置过期时间为 毫秒
        jedis.set("k3", "v3", new SetParams().px(8000));
        jedis.pttl("k3");


        // 4.支持 EXAT 和 PXAT 选项，用于设置键的绝对过期时间。EXAT 以秒为单位，PXAT 以毫秒为单位。这些选项可以用于精确控制键的过期时间点。
        long unixTimeSeconds = 2800000000L;
        jedis.set("k4", "v4", new SetParams().exAt(unixTimeSeconds));
        long unixTimeMilliseconds = 2800000000000L;
        jedis.set("k5", "v5", new SetParams().pxAt(unixTimeMilliseconds));


        // 5.保留过期时间 并更新 key-value
        jedis.set("k2", "v111", new SetParams().keepttl());

    }
}
