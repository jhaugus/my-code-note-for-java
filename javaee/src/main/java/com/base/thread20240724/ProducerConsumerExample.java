package com.base.thread20240724;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class ProducerConsumerExample {
    public static void main(String[] args) throws InterruptedException {
        // SynchronousQueue中不保存元素，始终为空
        SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>();
        // put take offer poll操作如果不设置超时时间,必须有两个及以上线程，形成生成-消费模型

        synchronousQueue.offer(1, 2, TimeUnit.SECONDS);
        Integer poll = synchronousQueue.poll(2, TimeUnit.SECONDS);
        System.out.println(poll);


        // 启动生产者线程
        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 2; i++) {
                    synchronousQueue.put(i); // 将数据放入队列
                    System.out.println("Produced " + i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // 启动消费者线程
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 2; i++) {
                    int item = synchronousQueue.take(); // 从队列中取出数据
                    System.out.println("Consumed " + item);

                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
