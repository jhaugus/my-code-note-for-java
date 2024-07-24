package com.base.thread20240724;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class DefaultThreadFactoryExample {

    private static int count = 0;
    public static void main(String[] args) {
        // 使用默认的线程工厂创建一个固定大小为3的线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        ReentrantLock reentrantLock = new ReentrantLock();


        // 提交10个任务到线程池中
        for (int i = 0; i < 10; i++) {
            final int taskNumber = i + 1;

            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    reentrantLock.lock();
                    System.out.println("Task " + taskNumber + " is running on " + Thread.currentThread().getName() + ": count = " + count);
                    try {
                        // 模拟任务执行时间
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                    reentrantLock.unlock();
                }
            });


        }

        // 关闭线程池
        fixedThreadPool.shutdown();
    }
}
