package com.company.concurrent.c_022;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal线程局部变量
 * <p>
 * 使用空间换时间，sychronized是使用时间换空间
 * 比如hibernate中的session就存在Thread Local中避免synchronized的使用
 */
public class ThreadLocal2 {
    static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tl.get());
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tl.set(new Person());
        }).start();
    }

    static class Person {
        String name = "zhang";
    }
}

