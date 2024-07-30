package com.base.thread20240724;

public class ThreadStateTestTerminal {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            // 线程任务
        });



        thread.start();
        thread.join(); // 等待线程结束
        System.out.println(thread.getState()); // TERMINATED


    }
}
