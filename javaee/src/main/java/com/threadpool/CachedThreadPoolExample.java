package com.threadpool;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//CachedThreadPool：根据需要创建新线程的线程池，但在先前构造的线程可用时将重用它们。
public class CachedThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " is executing a task.");
            });
        }

        executorService.shutdown();
    }
}
