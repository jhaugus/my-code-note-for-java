package com.base.ee002_Collections;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class LinkedHashMapTest {


    public static void main(String[] args) {
        LinkedHashMap<Integer, Integer> linkedHashMap = new LinkedHashMap<>();

        linkedHashMap.put(1, 1);

        linkedHashMap.put(2, 2);

        linkedHashMap.put(3, 3);

        linkedHashMap.put(4, 4);



        Iterator<Integer> iterator = linkedHashMap.keySet().iterator();
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            Integer value = linkedHashMap.get(key);
            System.out.println(key + ":" + value);
        }

       linkedHashMap.forEach((key1, value1) -> System.out.println("key: " + key1 + " value: " + value1));


    }
}
