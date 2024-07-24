package com.base.thread20240724;

public class ThreadStateTestTimeWait {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();
        Thread.sleep(100); // 确保线程进入计时等待状态
        System.out.println(thread.getState()); // TIMED_WAITING
    }
}
