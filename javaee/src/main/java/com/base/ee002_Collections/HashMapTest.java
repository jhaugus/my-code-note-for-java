package com.base.ee002_Collections;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapTest {


    @Test
    public void MapTest1(){
//        HashMap：基于哈希表实现，不保证元素的顺序。查找、插入和删除操作的平均时间复杂度为 O(1)。
//        LinkedHashMap：继承自 HashMap，维护了元素的插入顺序，遍历元素时按照插入顺序输出。
//        TreeMap：基于红黑树实现，元素按照自然顺序或指定的比较器顺序进行排序。
//        ConcurrentHashMap：线程安全的 HashMap，适用于多线程环境。
//        Hashtable：线程安全的哈希表实现，但性能略逊于 ConcurrentHashMap。不建议在新代码中使用，推荐使用 ConcurrentHashMap 替代。
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        LinkedHashMap<Integer, Integer> linkedHashMap = new LinkedHashMap<>();
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        ConcurrentHashMap<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        Hashtable<Integer, Integer> map1 = new Hashtable<>();

        hashMap.put(1, 1);
        hashMap.get(1);
        hashMap.replace(1, 29);  // 更新
        hashMap.compute(1, (k, v) -> v + 1);    // 如果存在则更新，否则添加
        hashMap.computeIfAbsent(2, k -> 1);  // 如果不存在则添加
        hashMap.computeIfPresent(1, (k, v) -> v + 1); // 如果存在则更新
        boolean b = hashMap.containsKey(1);
        boolean b1 = hashMap.containsValue(1);
        Integer orDefault = hashMap.getOrDefault(1, 10000000);

        //遍历
        hashMap.forEach((key, value) -> System.out.println(key + " : " + value));



//        System.out.println(hashMap.compute(1, (k, v) -> v + 1));  // 更新
        System.out.println(hashMap.get(1));
        System.out.println(hashMap.get(2));

//        hashMap.remove(1);

    }




}
