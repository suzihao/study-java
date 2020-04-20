package com.company.concurrent.c_019;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
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
 * <p>
 * notify之后，t1必须释放锁，t2退出后，也必须notify,通知t1继续执行
 * 整个通信过程比较繁琐
 * <p>
 * 使用Latch(门闩)替代wait notify
 * 优点：通信方式简单，同时可以指定等待时间
 * 使用await和countdown方法替代wait notify
 * CountDownLatch不涉及锁定，当count的值为零时当前线程继续执行
 * 当不涉及<同步>只是设计线程<通信>的时候 用synchronized+wait/notify就太重了
 * 这是应考虑countdownlatch/cyclicbarrier/semaphore
 */
public class MyContainer5 {
    //添加volatile 使得t2能够得到通知
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        MyContainer5 c = new MyContainer5();
        CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {
            System.out.println("t2 start");
            if (c.size() != 5) {
                try {
                    latch.await();
                    //也可指定等待时间
                    //latch.wait(5000,TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2 end");
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println("t1 start");
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add " + i);

                if (c.size() == 5) {
                    //打开门闩，让t2得以执行
                    latch.countDown();
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();
    }
}
