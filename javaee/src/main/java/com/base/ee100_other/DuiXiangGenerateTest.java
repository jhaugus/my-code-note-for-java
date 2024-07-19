package com.base.ee100_other;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class DuiXiangGenerateTest {


    @Test
    public void test1(){
        // 利用反射创建对象Person
        try {
            // 获取 Person 类的 Class 对象
            Class<?> clazz = Class.forName("com.base.ee003_serialize.Person");

            // 获取有参构造函数
            Constructor<?> constructor = clazz.getConstructor(String.class, int.class);

            // 使用有参构造函数创建对象
            Object person = constructor.newInstance("张三", 25);
            System.out.println(person);

            Method play = clazz.getMethod("play", String.class);
            Object res = play.invoke(person, "篮球");
            System.out.println(res);


            // 获取无参构造函数
            Constructor<?> noArgConstructor = clazz.getConstructor();

            // 使用无参构造函数创建对象
            Object person2 = noArgConstructor.newInstance();
            System.out.println(person2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
