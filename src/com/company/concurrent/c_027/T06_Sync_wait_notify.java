package com.company.concurrent.c_027;

/**
 * 用两个线程，一个输出字母一个输出数字
 * 交替输出1A2B3C...
 * <p>
 * 6：wait_notify
 * 注意：控制t1 2启动顺序！
 */
public class T06_Sync_wait_notify {

    private static volatile boolean t2Started = false;

    //private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) {

        final Object o = new Object();

        char[] aI = "123456789".toCharArray();
        char[] aC = "ABCDEFGHI".toCharArray();

        new Thread(() -> {
            //latch.await()
            synchronized (o) {

                while (!t2Started) {
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for (char c : aI) {
                    System.out.print(c);
                    try {
                        o.notify();
                        //让出锁
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //必须，否则无法停止程序
                o.notify();
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (o) {
                for (char c : aC) {
                    System.out.print(c);
                    //latch.countDown();
                    t2Started = true;

                    try {
                        o.notify();
                        //让出锁
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //必须，否则无法停止程序
                o.notify();
            }
        }, "t2").start();

    }

}
