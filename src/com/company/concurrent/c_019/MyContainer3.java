package com.company.concurrent.c_019;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 实现一个容器，提供add size方法
 * 写两个线程 线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5时，线程2给出提示并结束
 * <p>
 * 给lists添加volatile后，t2能够收到通知，但其线程死循环浪费CPU 因此：
 * 使用wait notify :wait会释放锁，而notify不会释放锁
 * 需要注意的是：使用这种方法必须保证t2先执行，即让监听先启动
 * <p>
 * 但下列代码其实是：
 * 输出结果并不是size=5时t2退出，而是t1结束时t2才接受到通知而推出
 * （原因：notify未释放锁）
 */
public class MyContainer3 {
    //添加volatile 使得t2能够得到通知
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        MyContainer3 c = new MyContainer3();
        final Object lock = new Object();

        new Thread(() -> {
            System.out.println("t2 start");
            synchronized (lock) {
                if (c.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 end");
            }

        }, "t2").start();

        new Thread(() -> {
            System.out.println("t1 start");
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    c.add(new Object());
                    System.out.println("add " + i);

                    if (c.size() == 5) {
                        lock.notify();
                    }

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1").start();
    }
}
