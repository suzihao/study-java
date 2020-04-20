package com.company.concurrent.c_010;

/**
 * 一个同步方法可以调用另一个同步方法，一个线程已经拥有了某个对象的锁，再次申请的时候仍然会得到该对象的锁
 * 也就是说 synchronized 锁是可重入的
 *
 * 这里是继承的情况下，子类调用父类的同步方法
 */
public class T {
    synchronized void m() {
        System.out.println(Thread.currentThread().getName() + " m start");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m end");
    }

    public static void main(String[] args) {
        new TT().m();
    }
}

class TT extends T {
    @Override
    synchronized void m() {
        System.out.println("child start");
        super.m();
        System.out.println("child end");
    }

}
