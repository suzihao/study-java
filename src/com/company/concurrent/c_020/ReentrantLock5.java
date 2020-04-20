package com.company.concurrent.c_020;

import java.util.concurrent.locks.ReentrantLock;

/**
 * reentrantlock用于替代synchronized
 * 本例中由于m1锁定this,只有m1执行完毕才能执行m2
 * 即synchronized的原始语义
 * <p>
 * 使用reentrantlock可以完成同样的功能
 * 需要注意的是：必须要必须要必须要手动释放锁！！！！！！
 * 使用syn锁定如果遇到异常，jvm自动释放锁，但lock必须手动释放
 * 所以经常在finally中进行锁的释放
 * <p>
 * 使用reentrantlock可以进行“尝试锁定”trylock,这样无法锁定或者在指定时间内无法锁定，线程可以决定是否继续等待
 * <p>
 * 使用reentrantlock还可以调用lockInterruptibly方法，可以对线程interrupt方法做出响应
 * 在一个线程等待锁的过程中，可以被打断
 * <p>
 * reentrantlock还可以指定为《公平锁》
 */
public class ReentrantLock5 extends Thread {
    //参数为true表示为公平锁 对比输出结果
    private static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获得锁");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLock5 r5 = new ReentrantLock5();
        Thread t1 = new Thread(r5);
        Thread t2 = new Thread(r5);
        t1.start();
        t2.start();
    }

}
