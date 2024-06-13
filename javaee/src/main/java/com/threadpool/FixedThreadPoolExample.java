package com.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
// FixedThreadPool：固定大小的线程池。
public class FixedThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " is executing a task.");
            });
        }

        executorService.shutdown();
    }
}

