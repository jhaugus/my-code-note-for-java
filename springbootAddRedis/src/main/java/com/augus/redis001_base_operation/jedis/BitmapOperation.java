package com.augus.redis001_base_operation.jedis;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

@Slf4j
public class BitmapOperation {


    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);

        // 设置位图的某一位
        jedis.setbit("bitmap", 0, true);
        jedis.setbit("bitmap", 1, true);
        jedis.setbit("bitmap", 2, false);



        // 获取位图的某一位
        Boolean bit = jedis.getbit("bitmap", 0);
        System.out.println("Bit value: " + bit);


        // 获取bitmap中的true的个数
        long bitCount = jedis.bitcount("bitmap");
        System.out.println("Number of bits set to 1: " + bitCount);

        jedis.close();
    }
}
