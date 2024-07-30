package com.base.thread20240724;

import javax.swing.*;
import java.util.Arrays;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(500, 1000, 10, TimeUnit.MILLISECONDS, new SynchronousQueue<>());

        CountDownLatch latch = new CountDownLatch(1000);

        for (int i = 0; i < 1000; i++) {
            threadPoolExecutor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + ":" + count++);
                latch.countDown();
            });
        }
        latch.await();
        threadPoolExecutor.shutdown();
    }
}
