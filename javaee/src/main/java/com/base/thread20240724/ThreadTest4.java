package com.base.thread20240724;

import java.util.concurrent.*;

public class ThreadTest4 {
    private static final Object lock = new Object();
    private static int counter = 1;

    public static void main(String[] args) {
//        ExecutorService executor = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(1000));
        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(() -> {
                synchronized (lock) {
                    if(counter < 100){
                        System.out.println(Thread.currentThread().getName() + " - " + counter);
                        counter++;
                    }
                }

            });
        }

        threadPoolExecutor.shutdown();
    }
}
