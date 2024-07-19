package com.base.ee005_ZhuJie;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface MyCustomAnnotation {
    String name() default "";
    int age() default 0;
}

// 使用自定义注解的类
@MyCustomAnnotation(name = "张三", age = 25)
class Person {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        // 通过反射获取注解信息
        MyCustomAnnotation annotation = Person.class.getAnnotation(MyCustomAnnotation.class);
        if (annotation!= null) {
            System.out.println("Name: " + annotation.name());
            System.out.println("Age: " + annotation.age());
        }
    }
}