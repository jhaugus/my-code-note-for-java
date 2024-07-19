package com.base.ee001_3_baseCharacter;
// 2024年6月25日19:44:39
/**
 * 测试Java的菱形继承问题
 * 如果实现了多个接口，接口中的默认方法相同的话，那么继承的类必须重写该方法
 */
public interface IntefaceTest1 {
    void method1();

    default void method2(){
        System.out.println("IntefaceTest1()");
    }
}
