package com.base.ee002_Collections;

public class HashTableTest {
    public static void main(String[] args) {

        // 创建一个HashTable对象
        java.util.Hashtable<String, Integer> hashtable = new java.util.Hashtable<>();

        // 添加键值对
        hashtable.put("apple", 1);
        hashtable.put("banana", 2);
        hashtable.put("cherry", 3);



        // 获取键对应的值
        int appleValue = hashtable.get("apple");
        System.out.println("apple的值为：" + appleValue);
    }
}
