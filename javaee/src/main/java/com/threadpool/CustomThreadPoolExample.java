package com.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
//ThreadPoolExecutor 类提供了更大的灵活性，允许我们自定义线程池的行为。
public class CustomThreadPoolExample {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2, // core pool size
                4, // maximum pool size
                60, // idle thread keep-alive time
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(2) // work queue with a capacity of 2
        );

        for (int i = 0; i < 8; i++) {
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " is executing a task.");
                try {
                    Thread.sleep(2000); // simulate a task taking some time to complete
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executor.shutdown();
    }
}
