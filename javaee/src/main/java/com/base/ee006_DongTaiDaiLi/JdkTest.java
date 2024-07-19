package com.base.ee006_DongTaiDaiLi;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkTest {


    public static void main(String[] args) {
        Service realService = new RealService();
        InvocationHandler handler = new JDKProxyHandler(realService);

        Service proxyService = (Service) Proxy.newProxyInstance(
                Service.class.getClassLoader(),
                new Class[]{Service.class},
                handler
        );

        proxyService.performTask();
    }
}
interface Service {
    void performTask();
}

class RealService implements Service {
    @Override
    public void performTask() {
        System.out.println("RealService is performing the task.");
    }
}

class JDKProxyHandler implements InvocationHandler {
    private Object target;

    public JDKProxyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before invoking the method in JDK proxy.");
        Object result = method.invoke(target, args);
        System.out.println("After invoking the method in JDK proxy.");
        return result;
    }
}