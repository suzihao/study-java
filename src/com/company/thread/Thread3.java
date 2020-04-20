package com.company.thread;

import java.util.concurrent.Callable;

public class Thread3 implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "线程";
    }

    public static void main(String[] args) {
        Thread3 th = new Thread3();
//        new Thread(th,"a").start();
    }
}
