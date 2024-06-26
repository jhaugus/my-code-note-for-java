package com.base.ee001_3_baseCharacter;

import org.junit.Test;
// 2024年6月25日16:13:25 测试抽象类的用法
public abstract class AbstractClass {

    int a = 1;
    int b = 2;
    public void method(){
        System.out.println("method");
    }

    @Test
    public void test(){
//        AbstractClass abstractClass = new AbstractClass();
        // 抽象类不能实例化
    }
}



