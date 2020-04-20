package com.company.concurrent.c_006;

/**
 * synchronized关键字： 对某个对象加锁
 */
public class T {

    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + " m1 start...");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m1 end...");
    }

    public void m2() {
        System.out.println(Thread.currentThread().getName() + " m2 start...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m2 end...");
    }

    public static void main(String[] args) {
        T t = new T();
        //lamda
        /**
         * 同 new Thread(new Runnable(){
         * @ovride
         * public void run(){}
         * })
         */
        new Thread(() -> t.m1(), "t1").start();
        new Thread(() -> t.m2(), "t2").start();
    }

}
