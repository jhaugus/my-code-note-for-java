package com.base.thread20240724;

public class ThreadStateTestBlock {
    public static void main(String[] args) throws InterruptedException {
        final Object lock = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    Thread.sleep(1000); // 保持锁1秒钟
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 2 acquired the lock");
            }
        });

        thread1.start();
        Thread.sleep(100); // 确保thread1先获取锁
        thread2.start();

        Thread.sleep(100); // 等待thread2尝试获取锁
        System.out.println(thread2.getState()); // BLOCKED
    }
}
