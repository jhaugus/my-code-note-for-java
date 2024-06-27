package com.base.ee002_Collections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

public class BlockingQueueTest {

    public static void main(String[] args) {
        // 创建一个大小为 5 的阻塞队列
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(5);

        // 消费者线程，从队列中获取元素
        new Thread(() -> {
            try {
                String element = queue.take();
                System.out.println("Consumed: " + element);
                element = queue.take();
                System.out.println("Consumed: " + element);
                element = queue.take();
                System.out.println("Consumed: " + element);
                element = queue.take();
                System.out.println("Consumed: " + element);
                element = queue.take();
                System.out.println("Consumed: " + element);
                // 尝试再获取一个，此时队列为空，会阻塞
                element = queue.take();
                System.out.println("Consumed: " + element);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // 生产者线程，向队列中添加元素
        new Thread(() -> {
            try {
                queue.put("Apple");
                System.out.println("添加: Apple");
                queue.put("Banana");
                System.out.println("添加: Banana");
                queue.put("Orange");
                System.out.println("添加: Orange");
                queue.put("Grape");
                System.out.println("添加: Grape");
                queue.put("Mango");
                System.out.println("添加: Mango");
                // 尝试再添加一个，此时队列已满，会阻塞
                queue.put("Kiwi");
                System.out.println("添加: Kiwi");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
