package com.base.ee001_3_baseCharacter;
// 2024年6月25日19:44:39

import org.junit.Test;

/**
 * 测试Java的菱形继承问题
 * 如果实现了多个接口，接口中的默认方法相同的话，那么继承的类必须重写该方法
 */
public class IntefaceTest4 implements IntefaceTest2{
    @Override
    public void method1() {

    }

//    @Test
//    public void test(){
//        IntefaceTest4 intefaceTest4 = new IntefaceTest4();
//        intefaceTest4.method2();
//    }
}
