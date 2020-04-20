package com.company.concurrent.c_004;

/**
 * synchronized关键字： 对某个对象加锁
 */
public class T {

    private static int count = 10;

    public synchronized static void m() {
        //这里等同于synchronized（c_004.T.class）
        count--;
        System.out.println(Thread.currentThread().getName() + " count=" + count);
    }

    public static void mm() {
        //这里无法使用synchronized（this）加锁，静态方法没有this
        synchronized (T.class) {
            //这里无法加锁
            count--;
        }
    }

}
