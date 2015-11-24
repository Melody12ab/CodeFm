package com.Intercpt.proxy;

import com.Intercpt.Iface.Interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by xiaobai on 15-11-24.
 */
public class TargetInterceptor implements InvocationHandler {

    private Object target;
    private Interceptor interceptor;

    private TargetInterceptor(Object target,Interceptor interceptor){

        this.target=target;
        this.interceptor=interceptor;
    }

    //将拦截逻辑封装到拦截器中，有客户端生成目标类的代理类的时候一起传入，这样客户端就可以设置不同的拦截逻辑
    public static Object bind(Object target,Interceptor interceptor){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),new TargetInterceptor(target,interceptor));

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.printf("Begin");
        //执行客户端定义的拦截逻辑
        interceptor.intercept(method,args);
        return method
                .invoke(target,args);
    }
}
