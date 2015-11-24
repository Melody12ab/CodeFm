package com.Intercpt.Iface.impl;

import com.Intercpt.Annotation.MethodName;
import com.Intercpt.Iface.NInterceptor;
import com.Intercpt.Vo.Invocation;

/**
 * Created by xiaobai on 15-11-24.
 */
@MethodName("execute1")
public class AInterceptorImpl implements NInterceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("A A A");
        return invocation.proceed();
    }
}
