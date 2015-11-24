package com.Intercpt.Main;

import com.Intercpt.Iface.AITarget;
import com.Intercpt.Iface.NInterceptor;
import com.Intercpt.Iface.impl.AInterceptorImpl;
import com.Intercpt.Iface.impl.AItargetImpl;
import com.Intercpt.proxy.TargetAIterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by xiaobai on 15-11-24.
 */
public class TestAInterceptor {
    public static void main(String[] args) {
        NInterceptor nInterceptor=new AInterceptorImpl();
        AITarget target=new AItargetImpl();
        target= (AITarget) TargetAIterceptor.bind(target,nInterceptor);
        target.execute1();
        target.execute2();
    }
}
