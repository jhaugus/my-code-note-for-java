package com.base.ee001_3_baseCharacter;

import org.junit.Test;
// 2024年6月25日16:13:25 测试抽象类的用法
public class ImplementAbstactClass extends AbstractClass{


    @Override
    public void method() {
        System.out.println("子类实现");
    }

    @Test
    public void test(){
        ImplementAbstactClass implementAbstactClass = new ImplementAbstactClass();

        implementAbstactClass.method();
    }
}
