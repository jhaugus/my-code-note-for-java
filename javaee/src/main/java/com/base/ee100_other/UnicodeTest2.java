package com.base.ee100_other;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class UnicodeTest2 {
    public static void main(String[] args) {
        String str = "中文测试";
        byte[] utf8Bytes = str.getBytes(StandardCharsets.UTF_16);

        byte[] bytes = convertUtf16ToUtf8(utf8Bytes);
        // 将 UTF-8 编码的字节数组转换为 String
        String utf8String = new String(bytes, Charset.forName("UTF-8"));

        System.out.println("转换后的 UTF-8 字符串: " + utf8String);
    }
    public static byte[] convertUtf16ToUtf8(byte[] gbkBytes) {
        try {
            String gbkString = new String(gbkBytes, "UTF_16");
            return gbkString.getBytes("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
