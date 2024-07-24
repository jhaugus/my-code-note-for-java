package com.base.thread20240724;

import java.util.concurrent.*;

public class CustomThreadFactoryExample {
    public static void main(String[] args) {
        // 自定义ThreadFactory
        ThreadFactory customThreadFactory = new ThreadFactory() {
            private int counter = 0;
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("CustomThread-" + counter++);
                thread.setDaemon(false); // 可以设置为守护线程
                return thread;
            }
        };

        // 使用自定义的ThreadFactory创建固定大小的线程池
        ExecutorService fixedThreadPool = new ThreadPoolExecutor(
            3, 3, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(),
            customThreadFactory
        );

        // 提交10个任务到线程池中
        for (int i = 0; i < 10; i++) {
            final int taskNumber = i + 1;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Task " + taskNumber + " is running on " + Thread.currentThread().getName());
                    try {
                        // 模拟任务执行时间
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Task " + taskNumber + " completed on " + Thread.currentThread().getName());
                }
            });
        }

        // 关闭线程池
        fixedThreadPool.shutdown();
    }
}
