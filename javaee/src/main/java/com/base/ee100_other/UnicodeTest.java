package com.base.ee100_other;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class UnicodeTest {

    public static void main(String[] args) {
        String str = "中文测试";

        // 使用 UTF-8 编码
        byte[] utf8Bytes = str.getBytes(StandardCharsets.UTF_8);
        System.out.println("UTF-8 编码后的字节数组长度: " + utf8Bytes.length);
        for (byte b : utf8Bytes) {
            System.out.print(b + " ");
        }
        System.out.println();

        // 使用 GBK 编码
        byte[] gbkBytes = str.getBytes(StandardCharsets.UTF_16);
        System.out.println("GBK 编码后的字节数组长度: " + gbkBytes.length);
        for (byte b : gbkBytes) {
            System.out.print(b + " ");
        }
        System.out.println();





    }



}
