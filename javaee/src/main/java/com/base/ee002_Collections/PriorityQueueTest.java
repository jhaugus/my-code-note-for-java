package com.base.ee002_Collections;

import java.util.PriorityQueue;

public class PriorityQueueTest {
    public static void main(String[] args) {
        // 创建一个默认自然顺序的优先级队列（这里假设元素是整数，自然顺序即从小到大）
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        // 向队列中添加元素
        priorityQueue.add(5);
        priorityQueue.add(3);
        priorityQueue.add(7);
        priorityQueue.add(1);
        priorityQueue.add(9);

        // 取出并打印队列中的元素
        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll());
        }
    }
}
