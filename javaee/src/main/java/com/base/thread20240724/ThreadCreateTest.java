package com.base.thread20240724;

public class ThreadCreateTest {


    private static int count = 0;
    public static void main(String[] args) {

        while (true) {
            new Thread(() -> {
                // 一些消耗资源的操作
                while (true) {
                    // 模拟线程内的持续操作
                    System.out.println(count++);
                }
            }).start();
        }
    }

}
