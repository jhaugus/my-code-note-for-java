package com.base.thread20240724;

public class ThreadStateTestNew {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            // 线程任务
        });
        System.out.println(thread.getState()); // NEW

    }
}
