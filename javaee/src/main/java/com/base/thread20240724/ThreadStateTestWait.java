package com.base.thread20240724;

public class ThreadStateTestWait {
    public static void main(String[] args) throws InterruptedException {
        final Object lock = new Object();

        Thread thread = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        Thread.sleep(100); // 确保线程进入等待状态
        System.out.println(thread.getState()); // WAITING

        synchronized (lock) {
            lock.notify();
        }
    }
}
