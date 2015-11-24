package com.Intercpt.Iface;

import java.lang.reflect.Method;

/**
 * Created by xiaobai on 15-11-24.
 */
public interface Interceptor {
    public void intercept(Method method,Object [] args);
}
