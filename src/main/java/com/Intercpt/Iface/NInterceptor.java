package com.Intercpt.Iface;

import com.Intercpt.Vo.Invocation;

import java.lang.reflect.Method;

/**
 * Created by xiaobai on 15-11-24.
 */
public interface NInterceptor {
    public Object intercept(Invocation invocation) throws Throwable;
}
