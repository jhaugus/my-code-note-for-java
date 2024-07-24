package com.base.thread20240724;

public class ThreadTest2 implements Runnable{
    private static int count = 1;
    @Override
    public void run() {
        synchronized (this){
            while(count < 100){
                System.out.println(Thread.currentThread().getName() + " " + count++);
            }
        }
    }


    public static void main(String[] args) {
        ThreadTest2 threadTest2 = new ThreadTest2();
        Thread thread = new Thread(threadTest2);
        Thread thread1 = new Thread(threadTest2);
        thread.start();
        thread1.start();
    }
}
