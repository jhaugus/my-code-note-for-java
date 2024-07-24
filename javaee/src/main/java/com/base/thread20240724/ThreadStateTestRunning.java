package com.base.thread20240724;

public class ThreadStateTestRunning {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {
                // 线程任务
            }
        });
        thread.start();
        System.out.println(thread.getState()); // RUNNABLE

    }
}
