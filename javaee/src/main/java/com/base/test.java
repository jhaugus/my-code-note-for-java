//package com.base;
//
//import java.util.concurrent.ConcurrentLinkedDeque;
//
//public class test {
//
////    题目：设计单链表结构的对象，用于表示一个超大的正整数，
////    链表的每个节点表示其中一位，链表从高位指向低位，比如正整数为123,
////    则链表结构为：1-&gt;2-&gt;3-&gt;null，
////    题目要求：使用多线程对链表进行加1操作，输入是一个链表，输出还是链表。
//    public static void main(String[] args) {
//        double num = 3.14159;
//        String result = String.format("%.2f", num);
//        System.out.println(result);
//
//    }
//
//
//
//}
//
//class MyThread extends Thread{
//    private ConcurrentLinkedDeque<Node> queue;
//
//    public MyThread(ConcurrentLinkedDeque<Node> queue) {
//        this.queue = queue;
//    }
//
//
//    @Override
//    public void run(){
//        boolean carry = true;
//        while(carry){
//            Node node = queue.poll();
//            if (node != null) {
//                if(node.val < 9){
//                    node.val++;
//                    carry = false;
//                }else{
//                    node.val = 0;
//                }
//            }else{
//                Node node1 = new Node(1);
//                queue.add(node1);
//                carry = false;
//            }
//        }
//    }
//}
//class Node{
//    int val;
//    Node next;
//
//    public Node(int val) {
//        this.val = val;
//        this.next = null;
//    }
//}
