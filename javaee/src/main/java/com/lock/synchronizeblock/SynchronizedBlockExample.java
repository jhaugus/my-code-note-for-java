package com.lock.synchronizeblock;

public class SynchronizedBlockExample {
    public static void main(String[] args) {
        Counter counter = new Counter();

        // 创建多个线程，调用 Counter 的 increment 方法
        Thread thread1 = new Thread(new CounterIncrementTask(counter));
        Thread thread2 = new Thread(new CounterIncrementTask(counter));
        Thread thread3 = new Thread(new CounterIncrementTask(counter));

        // 启动线程
        thread1.start();
        thread2.start();
        thread3.start();

        // 等待所有线程执行完毕
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 输出计数器的最终值
        System.out.println("Final count: " + counter.getCount());
    }
}

// 定义任务类，在线程中调用 Counter 的 increment 方法
class CounterIncrementTask implements Runnable {
    private Counter counter;

    public CounterIncrementTask(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.increment();
        }
    }
}
