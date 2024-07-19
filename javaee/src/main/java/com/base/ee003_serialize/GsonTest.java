package com.base.ee003_serialize;

import com.google.gson.Gson;

public class GsonTest {

    public static void main(String[] args) {
        // 创建一个对象
        Person person = new Person("张三", 25);

        // 使用 Gson 进行序列化
        Gson gson = new Gson();
        String jsonString = gson.toJson(person);
        System.out.println("序列化后的 JSON 字符串: " + jsonString);


        // 反序列化
        Person person2 = gson.fromJson(jsonString, Person.class);
        System.out.println("反序列化后的对象: " + person2);
    }
}
