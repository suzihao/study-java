package com.company.concurrent.c_027;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用两个线程，一个输出字母一个输出数字
 * 交替输出1A2B3C...
 * <p>
 * 4：AtomicInteger
 */
public class T04_AtomicInteger {

    static AtomicInteger threadNo = new AtomicInteger(1);

    public static void main(String[] args) {
        char[] aI = "123456789".toCharArray();
        char[] aC = "ABCDEFGHI".toCharArray();

        new Thread(() -> {
            for (char c : aI) {
                while (threadNo.get() != 1) {
                }
                System.out.print(c);
                threadNo.set(2);
            }
        }, "t1").start();

        new Thread(() -> {
            for (char c : aC) {
                while (threadNo.get() != 2) {
                }
                System.out.print(c);
                threadNo.set(1);
            }
        }, "t2").start();

    }

}
