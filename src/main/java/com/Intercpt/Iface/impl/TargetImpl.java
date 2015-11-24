package com.Intercpt.Iface.impl;

import com.Intercpt.Iface.ITarget;

/**
 * Created by xiaobai on 15-11-24.
 */
public class TargetImpl implements ITarget {
    @Override
    public void execute() {
        System.out.println("Execute");
    }
}
