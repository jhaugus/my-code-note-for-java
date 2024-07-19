package com.base.ee001_3_baseCharacter;

import java.util.ArrayList;
import java.util.List;

//泛型中上下界限定符extends 和 super有什么区别？
public class ExtendsSuperTest {

    public static <T extends Number> void fun1(T number){
        double v = number.doubleValue();
        System.out.println(v);
    }



    public static <T> void addElements(List<? super T> list, T element){
        list.add(element);
    }

    public static void main(String[] args) {
        fun1(new Integer(10));

        addElements(new ArrayList<>(), 4);
    }

}
