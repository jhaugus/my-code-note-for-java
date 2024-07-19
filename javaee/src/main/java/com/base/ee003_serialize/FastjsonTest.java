package com.base.ee003_serialize;

import com.alibaba.fastjson2.JSON;

public class FastjsonTest {


    public static void main(String[] args) {
        // 创建一个对象
        Person person = new Person("李四", 30);

        // 使用 Fastjson 进行序列化
        String jsonString = JSON.toJSONString(person);
        System.out.println("序列化后的 JSON 字符串: " + jsonString);


        Person person2 = JSON.parseObject(jsonString, Person.class);
        System.out.println("反序列化后的对象: " + person2);
    }

}
