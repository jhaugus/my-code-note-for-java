package com.threadpool;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
// SingleThreadExecutor：一个单线程的线程池，它能确保所有任务都在同一个线程中按顺序执行。
public class SingleThreadExecutorExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " is executing a task.");
            });
        }

        executorService.shutdown();
    }
}

