package com.base.ee100_other;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class UnicodeTest3 {
    public static void main(String[] args) {
        String str = "中文测试";
        byte[] utf8Bytes = str.getBytes(StandardCharsets.UTF_16);

        // 将 UTF-8 编码的字节数组转换为 String
        String utf8String = new String(utf8Bytes, Charset.forName("UTF-8"));

        System.out.println("转换后的 UTF-8 字符串: " + utf8String);
    }

}