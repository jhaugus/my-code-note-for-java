package com.threadpool;
//ScheduledThreadPool：可以用来调度延迟任务或周期性任务的线程池。
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);

        scheduledExecutorService.schedule(() -> {
            System.out.println(Thread.currentThread().getName() + " is executing a scheduled task.");
        }, 5, TimeUnit.SECONDS);

        scheduledExecutorService.shutdown();
    }
}

