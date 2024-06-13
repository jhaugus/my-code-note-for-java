package com.lock.synchronizeblock;

public class Counter {
    private int count = 0;

    // 同步代码块，确保只有一个线程可以同时访问该代码块
    public void increment() {
        synchronized (this) {
            count++;
        }
    }

    // 获取当前计数器的值
    public int getCount() {
        return count;
    }
}
