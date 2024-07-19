package com.base.ee006_DongTaiDaiLi;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

class Target {
    public void doOperation() {
        System.out.println("Target is doing the operation.");
    }
}

class CglibInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("Before the method call in CGLIB proxy.");
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("After the method call in CGLIB proxy.");
        return result;
    }
}

public class CGLIBDynamicProxyExample {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Target.class);
        enhancer.setCallback(new CglibInterceptor());
        Target proxy = (Target) enhancer.create();
        proxy.doOperation();
    }
}