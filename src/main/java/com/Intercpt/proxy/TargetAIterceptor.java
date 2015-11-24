package com.Intercpt.proxy;

import com.Intercpt.Annotation.MethodName;
import com.Intercpt.Iface.NInterceptor;
import com.Intercpt.Vo.Invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Objects;

/**
 * Created by xiaobai on 15-11-24.
 */
public class TargetAIterceptor implements InvocationHandler{
    private Object target;
    private NInterceptor nInterceptor;

    private TargetAIterceptor(Object target, NInterceptor nInterceptor){
        this.target=target;
        this.nInterceptor=nInterceptor;
    }

    //将拦截逻辑封装到拦截器中，有客户端生成目标类的代理类的时候一起传入，这样客户端就可以设置不同的拦截逻辑
    public static Object bind(Object target,NInterceptor nInterceptor){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), new TargetAIterceptor(target, nInterceptor));

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MethodName methodName=this.nInterceptor.getClass().getAnnotation(MethodName.class);
        if(Objects.isNull(methodName))
            throw new NullPointerException("null pointer");
        //如果注解上的方法名和该方法名一样，才拦截
        String name=methodName.value();
        if(name.equals(method.getName())){
            return nInterceptor.intercept(new Invocation(target,method,args));
        }
        return method.invoke(target,method,args);
    }
}
