package com.base.ee001_3_baseCharacter;
// 2024年6月25日19:44:39
import org.junit.Test;
/**
 * 测试Java的菱形继承问题
 * 如果实现了多个接口，接口中的默认方法相同的话，那么继承的类必须重写该方法
 */
public class IntefaceTest3 implements IntefaceTest1, IntefaceTest2{

    @Override
    public void method1() {

    }

    @Override
    public void method2() {
        IntefaceTest1.super.method2();
        System.out.println("IntefaceTest3");
    }


//    @Test
//    public void test(){
//        IntefaceTest3 intefaceTest3 = new IntefaceTest3();
//        intefaceTest3.method2();
//    }
}
