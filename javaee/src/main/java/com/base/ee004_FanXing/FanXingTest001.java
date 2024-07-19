package com.base.ee004_FanXing;


import org.junit.Test;

import java.util.ArrayList;

public class FanXingTest001 {


    @Test
    public void test1(){
//        testExtend(new FanXing1());  // 直接报错
        testExtend(new FanXing2());
        testExtend(new FanXing3());
    }

    // <T extends FanXing2> 表示T只能是继承FanXing2的
    public static <T extends FanXing2> void testExtend(T t){
        System.out.println(t);
    }




    @Test
    public void test2(){

        testExtend2(new ArrayList<FanXing2>(), new FanXing2());
        testExtend2(new ArrayList<FanXing2>(), new FanXing3());


    }
    public static <T> void testExtend2(ArrayList<? super T> list, T element){
        System.out.println(list);
        System.out.println(element);
    }
}
class Parent {}
class Child extends Parent {}

