package com.base.ee002_Collections;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class TreeMapTest {
    public static void main(String[] args) {

        // 创建TreeMap对象
        TreeMap<String, Integer> treeMap = new TreeMap<>();

        // 添加键值对
        treeMap.put("apple", 10);
        treeMap.put("banana", 5);
        treeMap.put("orange", 8);

        Map.Entry<String, Integer> banana = treeMap.ceilingEntry("banana");// 返回大于等于指定键的第一个键值对
        String banana1 = treeMap.ceilingKey("banana");// 返回大于等于指定键的第一个键
        String banana2 = treeMap.floorKey("banana");// 返回小于等于指定键的最大键
        Map.Entry<String, Integer> banana3 = treeMap.floorEntry("banana");// 返回小于等于指定键的最后一个键值对


        NavigableMap<String, Integer> stringIntegerNavigableMap
                = treeMap.descendingMap(); // 返回降序的NavigableMap

        for (String s : treeMap.descendingKeySet()) {
            System.out.println(s);
        }
        for (String s : treeMap.keySet()) {
            System.out.println(s);
        }
        for (Integer value : treeMap.values()) {
            System.out.println(value);
        }

        Map.Entry<String, Integer> stringIntegerEntry = treeMap.firstEntry(); // 返回第一个键值对


        // 遍历TreeMap
        for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
