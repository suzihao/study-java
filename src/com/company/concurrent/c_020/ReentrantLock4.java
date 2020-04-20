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
 * <p>
 * 使用reentrantlock还可以调用lockInterruptibly方法，可以对线程interrupt方法做出响应
 * 在一个线程等待锁的过程中，可以被打断
 */
public class ReentrantLock4 {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("t1 start");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                System.out.println("t1 end");
            } catch (InterruptedException e) {
                System.out.println("interrupted!");
            } finally {
                lock.unlock();
            }
        }, "t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            //lock.lock();
            //不可被打断
            try {
                // 可以对interrupt()方法做出相应
                lock.lockInterruptibly();
                System.out.println("t2 start");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("t2 end");
            } catch (InterruptedException e) {
                System.out.println("interrupted!");
            } finally {
                lock.unlock();
            }
        }, "t2");
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打断线程2的等待
        t2.interrupt();
    }

}
