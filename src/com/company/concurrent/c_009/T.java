package com.company.concurrent.c_009;

/**
 * 模拟死锁
 */
public class T {
    public static Object a = new Object();
    public static Object b = new Object();

    public void m1() {
        System.out.println(Thread.currentThread().getName() + " m1.start");
        synchronized (a){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (b){
                System.out.println("m1.end");
            }
        }
    }

    public void m2() {
        System.out.println(Thread.currentThread().getName() + " m2.start");
        synchronized (b){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (a){
                System.out.println("m2.end");
            }
        }
    }

    public static void main(String[] args) {
        T t = new T();
        new Thread(() -> t.m1(), "t1").start();
        new Thread(() -> t.m2(), "t2").start();
    }

}
