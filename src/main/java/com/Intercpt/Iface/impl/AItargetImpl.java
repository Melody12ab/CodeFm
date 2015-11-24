package com.Intercpt.Iface.impl;

import com.Intercpt.Iface.AITarget;

/**
 * Created by xiaobai on 15-11-24.
 */
public class AItargetImpl implements AITarget {
    @Override
    public void execute1() {
        System.out.println("execute one");
    }

    @Override
    public void execute2() {
        System.out.println("execute two");
    }
}
