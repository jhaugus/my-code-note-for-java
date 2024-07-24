package com.base.thread20240724;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadTest3 implements Callable {
    private static int i = 0;
    public static void main(String[] args) throws Exception {
        ThreadTest3 threadTest3 = new ThreadTest3();
        FutureTask<Object> objectFutureTask = new FutureTask<Object>(threadTest3);
        new Thread(objectFutureTask).start();


        ThreadTest3 threadTest4 = new ThreadTest3();
        FutureTask<Object> objectFutureTask2 = new FutureTask<Object>(threadTest4);
        new Thread(objectFutureTask2).start();

        System.out.println(objectFutureTask2.get());
    }

    @Override
    public Object call() throws Exception {
        synchronized (ThreadTest3.class){
            while(i < 100){
                System.out.println(Thread.currentThread().getName() + ":" + i++);
            }
        }
        return i;
    }
}
