package com.base.ee002_Collections;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HashSetTest {
    public static void main(String[] args) {

        // 创建一个HashSet对象
        HashSet<String> hashSet = new HashSet<>();

        // 向HashSet中添加元素
        hashSet.add("Apple");
        hashSet.add("ABanana");
        hashSet.add("AOrange");


        Spliterator<String> spliterator = hashSet.spliterator();
//        while (spliterator.tryAdvance(System.out::println));    // 使用Spliterator遍历HashSet

        boolean b = hashSet.stream().allMatch(s -> s.startsWith("A")); // 是否所有前缀都匹配
//        System.out.println(b);
        boolean b1 = hashSet.stream().anyMatch(s -> s.startsWith("A"));

        Stream<String> parallel = hashSet.stream().parallel(); // 创建并行流
//        parallel.forEach(System.out::println);


        // collect有很多用法
        List<String> collect = hashSet.stream().collect(Collectors.toList());
        collect.forEach(System.out::println);

        // 分组
//        5:[Apple]
//          7:[AOrange, ABanana]
        Map<Integer, List<String>> collect1 = hashSet.stream().collect(Collectors.groupingBy(s -> s.length()));
        collect1.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });


        // TODO HashSet中的stream流提供了很多的功能可以使用，如果要使用HashSet的时候可以认真研究



    }
}
