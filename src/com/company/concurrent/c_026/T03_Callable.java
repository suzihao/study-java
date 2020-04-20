package com.company.concurrent.c_026;

import java.util.concurrent.Callable;

/**
 * 认识Callable,对Runnable做了扩展,区别 ：返回值，异常
 */
public class T03_Callable implements Callable, Runnable {

    @Override
    public Object call() throws Exception {
        return null;
    }

    @Override
    public void run() {

    }
}
