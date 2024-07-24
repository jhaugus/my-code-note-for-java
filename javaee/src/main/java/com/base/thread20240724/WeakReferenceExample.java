package com.base.thread20240724;

import java.lang.ref.WeakReference;

public class WeakReferenceExample {
    public static void main(String[] args) {
        // 创建一个强引用对象
        String strongReference = new String("I am a strong reference");

        // 创建一个弱引用对象
        WeakReference<String> weakReference = new WeakReference<>(strongReference);

        // 输出强引用和弱引用指向的对象
        System.out.println("Before nullifying strong reference:");
        System.out.println("Strong Reference: " + strongReference);
        System.out.println("Weak Reference: " + weakReference.get());

        // 将强引用置为null
        strongReference = null;

        // 强制垃圾回收
        System.gc();

        // 休眠一段时间以等待垃圾回收
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 输出弱引用指向的对象
        System.out.println("After nullifying strong reference:");
        System.out.println("Strong Reference: " + strongReference);
        System.out.println("Weak Reference: " + weakReference.get());
    }
}