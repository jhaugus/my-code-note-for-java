package com.base.ee001_3_baseCharacter;



import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigDecimalTest {

    @Test
    public void test(){
        BigDecimal bigDecimal = new BigDecimal("1.0000");

        BigDecimal bigDecimal2 = new BigDecimal("1.00");


        // BigDecimal.equals()比较两个数的时候，不仅比较两个数的大小，还要比较两个数的标度，即长度
        System.out.println(bigDecimal2.equals(bigDecimal));


        // compareTo只比较大小，相等返回0
        System.out.println(bigDecimal2.compareTo(bigDecimal) == 0);


    }




}
