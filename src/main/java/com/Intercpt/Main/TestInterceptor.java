package com.Intercpt.Main;

import com.Intercpt.Iface.ITarget;
import com.Intercpt.Iface.Interceptor;
import com.Intercpt.Iface.impl.TargetImpl;
import com.Intercpt.proxy.TargetInterceptor;

import java.lang.reflect.Method;

/**
 * Created by xiaobai on 15-11-24.
 */
public class TestInterceptor {
    public static void main(String[] args) {
        Interceptor interceptor=new Interceptor() {
            @Override
            public void intercept(Method method, Object[] args) {
                //TODO judge args or method
                System.out.println("Go Go Go");
            }
        };
        ITarget target= new TargetImpl();
        target= (ITarget) TargetInterceptor.bind(target,interceptor);
        target.execute();
    }

}
