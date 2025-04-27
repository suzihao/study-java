package com.company.thread;


import java.util.concurrent.atomic.AtomicInteger;

public class Thread4 {

    static AtomicInteger atomicInteger = new AtomicInteger(0);
    static final Object ob = new Object();

    public static void main(String[] args) {
        new Thread(()->{
            while (atomicInteger.get()< 200){
                synchronized (ob){
                    ob.notify();
                    System.out.println(atomicInteger.incrementAndGet());
                    try {
                        ob.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            }

        }).start();

        new Thread(()->{
            while (atomicInteger.get()< 200){
                synchronized (ob){
                    ob.notify();
                    System.out.println(atomicInteger.incrementAndGet());
                    try {
                        ob.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();

    }
}
