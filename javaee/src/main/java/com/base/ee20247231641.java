package com.base;

import java.util.Stack;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class ee20247231641 {
    public static void main(String[] args) throws InterruptedException {
        // 线程不安全
        // 多个线程对同一个共享数据进行访问而不采取同步操作的话，那么操作的结果是不一致的

//        final int threadSize = 500;
//        ThreadUnsafeExample example = new ThreadUnsafeExample();
//        final CountDownLatch countDownLatch = new CountDownLatch(threadSize);
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        ReentrantLock reentrantLock = new ReentrantLock();
//        for(int i = 0; i < threadSize; i++){
//            executorService.execute(() -> {
//                reentrantLock.lock();
//                example.add();
//                countDownLatch.countDown();
//                reentrantLock.unlock();
//            });
//        }
//
//        countDownLatch.await();
//        executorService.shutdown();

        Node node = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node.next = node2;
        node2.next = node3;

//        Node res = method(node);
//        while(res != null){
//            System.out.println(res.val);
//            res = res.next;
//        }
        Node res = null;
        final int threadSize = 5000;
        ThreadUnsafeExample example = new ThreadUnsafeExample();
        final CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        ExecutorService executorService = Executors.newCachedThreadPool();
        ReentrantLock reentrantLock = new ReentrantLock();
        for(int i = 0; i < threadSize; i++){
            executorService.execute(() -> {
                reentrantLock.lock();
                method(node);
                countDownLatch.countDown();
                reentrantLock.unlock();
            });
        }

//        countDownLatch.await();
//        executorService.shutdown();
        Node q = node;
        while(q != null){
            System.out.print(q.val);
            q = q.next;
        }


    }

    public static synchronized Node method(Node nodes){
        Node res = null;
        Stack<Node> stack = new Stack<>();
        int rep = 1;
        Node p = nodes;
        while (p != null){
            nodes = nodes.next;
            p.next = null;
            stack.push(p);
            p = nodes;
        }

        Node pop = null;
        while(!stack.isEmpty()){
            pop = stack.pop();
            pop.val = pop.val + rep;
            if(pop.val >= 10){
                rep = 1;
                pop.val = pop.val - 10;
            }else {
                rep = 0;
            }

            pop.next = res;
            res = pop;

        }
        if(rep != 0){
            Node node = new Node(rep);
            node.next =res;
            res = node;
        }

        return res;
//        Node q = nodes;
//        while(q != null){
//            System.out.print(q.val);
//            q = q.next;
//        }
//        System.out.println();
    }
}

class ThreadUnsafeExample{

    static ConcurrentLinkedDeque<Node> queue = new ConcurrentLinkedDeque<>();

    public void add(Node node){
        queue.add(node);
    }


}


class Node{
    int val;
    Node next;

    public Node() {
    }

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}