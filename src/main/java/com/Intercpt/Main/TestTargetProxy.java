package com.Intercpt.Main;

import com.Intercpt.Iface.ITarget;
import com.Intercpt.Iface.impl.TargetImpl;
import com.Intercpt.proxy.TargetProxy;

/**
 * Created by xiaobai on 15-11-24.
 */
public class TestTargetProxy {
    public static void main(String[] args) {
        //before proxy
        ITarget target=new TargetImpl();
        target.execute();

        //after proxy
        target= (ITarget) TargetProxy.bind(target);
        target.execute();
    }
}
