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
 * <p>
 * 使用reentrantlock可以进行“尝试锁定”trylock,这样无法锁定或者在指定时间内无法锁定，线程可以决定是否继续等待
 */
public class ReentrantLock3 {
    Lock lock = new ReentrantLock();

    void m1() {
        //等同于synchronized(this)
        lock.lock();
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

    /**
     * 进行尝试锁定，不然锁定与否，方法都会继续执行
     * 可以根据trylock的返回值判定是否锁定
     * 也可以指定trylock的时间，由于trylock(time)抛出异常，
     * 要注意unlock的处理，必须放到finally中
     */
    void m2() {
        /*boolean locked = lock.tryLock();
        System.out.println("m2..."+locked);
        if(locked) lock.unlock();*/

        boolean locked = false;
        try {
            locked = lock.tryLock(2, TimeUnit.SECONDS);
            System.out.println("m2..." + locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (locked) {
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) {
        ReentrantLock3 r1 = new ReentrantLock3();
        new Thread(() -> r1.m1()).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> r1.m2()).start();
    }

}
