package com.lock.synchronize;

public class Counter {
    private int count = 0;

    // 同步方法，确保只有一个线程可以同时访问该方法
    public synchronized void increment() {
        count++;
    }

    // 获取当前计数器的值
    public int getCount() {
        return count;
    }
}
