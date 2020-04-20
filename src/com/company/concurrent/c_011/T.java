package com.company.concurrent.c_011;

/**
 * 程序在执行过程中出现异常，默认情况锁会被释放
 * 在并发处理过程中，有异常有多加小心，不然可能会出现不一致的情况
 * 比如一个web app 处理过程中，多个servlet共同访问同一个资源，这是如果异常处理不合适
 * 在第一个线程中抛出异常，其他线程进入同步代码区，有可能访问到异常产生时的数据
 * 因此需要十分小心处理同步业务逻辑中的异常
 */
public class T {
    int count = 0;

    synchronized void m() {
        System.out.println(Thread.currentThread().getName() + " start");
        while (true) {
            count++;
            System.out.println(Thread.currentThread().getName() + " count=" + count);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (count == 5) {
                //此处抛出异常，锁将被释放，要想不被释放，可以在这进行catch继续循环
                int i = 1 / 0;
            }
        }

    }

    public static void main(String[] args) {
        T t = new T();
        new Thread(() -> t.m(), "t1").start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> t.m(), "t2").start();
    }

}
