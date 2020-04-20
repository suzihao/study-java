package com.company.concurrent.c_027;

/**
 * 用两个线程，一个输出字母一个输出数字
 * 交替输出1A2B3C...
 * <p>
 * 二：自旋+volatile
 */
public class T02_CAS {

    enum ReadyToRun {T1, T2}

    //思考为什么必须volatile
    static volatile ReadyToRun r = ReadyToRun.T1;

    public static void main(String[] args) {
        char[] aI = "123456789".toCharArray();
        char[] aC = "ABCDEFGHI".toCharArray();

        new Thread(() -> {
            for (char c : aI) {
                while (r != ReadyToRun.T1) {
                }
                System.out.print(c);
                r = ReadyToRun.T2;
            }
        }, "t1").start();

        new Thread(() -> {
            for (char c : aC) {
                while (r != ReadyToRun.T2) {
                }
                System.out.print(c);
                r = ReadyToRun.T1;
            }
        }, "t2").start();


    }

}
