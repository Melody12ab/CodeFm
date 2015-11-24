package com.Intercpt.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by xiaobai on 15-11-24.
 */
public class TargetProxy implements InvocationHandler {
    private Object target;

    private TargetProxy(Object target){
        this.target=target;
    }

    //生成一个目标对象的代理对象
    public static Object bind(Object target){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),new TargetProxy(target));

    }

    //在执行目标对象方法前加上自己的拦截逻辑
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Begin");
        return method
                .invoke(target,args);
    }
}
