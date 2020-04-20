package com.company.concurrent.c_023;

import java.util.Arrays;

/**
 * 线程安全的单例模式
 * <p>
 * 更好的方式是如下，既不用加锁，还能实现懒加载
 */
public class Singleton {
    private Singleton() {
        System.out.println("singleton!");
    }

    private static class Inner {
        private static Singleton s = new Singleton();
    }

    private static Singleton getSingle() {
        return Inner.s;
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[200];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                Singleton.getSingle();
            });
        }

        Arrays.asList(threads).forEach(o -> o.start());
    }
}
