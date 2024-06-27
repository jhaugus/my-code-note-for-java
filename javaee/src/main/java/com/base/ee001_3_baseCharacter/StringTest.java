package com.base.ee001_3_baseCharacter;

import org.junit.Test;

public class StringTest {

    /**
     * 测试：String创建方式不同，引用地址是否相同
     */


    @Test
    public void test(){
        String s1 = "123";
        String s2 = new String("123");
        System.out.println(s1 == s2); // false
    }


    @Test
    public void test2(){
        String s1 = "123";
        String s2 = "1" + "23";
        System.out.println(s1 == s2); // true
    }


    @Test
    public void test3(){
        String s1 = new String("123");
        String s2 = new String("1") + new String("23");
        System.out.println(s1 == s2); // false

    }

    @Test
    public void test4(){
        String s1 = new String("123");
        String s2 = new String("123");
        System.out.println(s1 == s2);  // false
    }


}
