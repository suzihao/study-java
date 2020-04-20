package com.company.concurrent.c_027;

import java.util.concurrent.locks.LockSupport;

/**
 * 用两个线程，一个输出字母一个输出数字
 * 交替输出1A2B3C...
 * <p>
 * 一：使用LockSupport.park()  .unpark()
 */
public class T01_LockSupport {
    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        char[] aI = "123456789".toCharArray();
        char[] aC = "ABCDEFGHI".toCharArray();

        t1 = new Thread(() -> {
            for (char c : aI) {
                System.out.print(c);
                //叫醒t2
                LockSupport.unpark(t2);
                //t1阻塞
                LockSupport.park();
            }
        }, "t1");

        t2 = new Thread(() -> {
            for (char c : aC) {
                //t2阻塞
                LockSupport.park();
                System.out.print(c);
                //叫醒t1
                LockSupport.unpark(t1);
            }
        }, "t2");

        t1.start();
        t2.start();
    }

}
