package com.Intercpt.Main;

import com.Intercpt.Iface.ITarget;
import com.Intercpt.Iface.NInterceptor;
import com.Intercpt.Iface.impl.TargetImpl;
import com.Intercpt.Vo.Invocation;
import com.Intercpt.proxy.TargetNIterceptor;

/**
 * Created by xiaobai on 15-11-24.
 */
public class TestNInterceptor {
    public static void main(String[] args) {
        NInterceptor nInterceptor=new NInterceptor() {
            @Override
            public Object intercept(Invocation invocation) throws Throwable {
                System.out.println("Go Go Go");
                return invocation.proceed();
            }
        };
        ITarget target=new TargetImpl();
        target= (ITarget) TargetNIterceptor.bind(target,nInterceptor);
        target.execute();
    }
}
