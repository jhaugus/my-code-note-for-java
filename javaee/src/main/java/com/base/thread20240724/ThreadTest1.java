package com.base.thread20240724;

public class ThreadTest1 extends Thread{
    private static int i = 0;
    @Override
    public void run(){
        synchronized (ThreadTest1.class){
            while(i < 100){
                System.out.println(Thread.currentThread().getName() + ":" + i++);
            }
        }
    }
    public static void main(String[] args) {
        ThreadTest1 t1 =new ThreadTest1();
        ThreadTest1 t2 =new ThreadTest1();

        t1.start();
        t2.start();
    }


}
