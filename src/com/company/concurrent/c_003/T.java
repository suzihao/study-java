package com.company.concurrent.c_003;

/**
 * synchronized关键字： 对某个对象加锁
 */
public class T {

    private int count = 10;

    public synchronized void m() {
        //等同于在方法执行时需要synchronized（this）
        count--;
        System.out.println(Thread.currentThread().getName() + " count=" + count);
    }

}
