package com.company.concurrent.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
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
 */
public class ReentrantLock2 {
    Lock lock = new ReentrantLock();

    void m1() {
        lock.lock();//等同于synchronized(this)
        try {
            for (int i = 0; i < 5; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //手动释放！！！
            lock.unlock();
        }
    }

    void m2() {
        lock.lock();
        try {
            System.out.println("m2...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLock2 r1 = new ReentrantLock2();
        new Thread(() -> r1.m1()).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> r1.m2()).start();
    }

}
