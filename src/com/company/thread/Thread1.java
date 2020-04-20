package com.company.thread;

public class Thread1 implements Runnable{


    @Override
    public void run() {
        System.out.println("这是一个线程");
    }

    public static void main(String[] args) {
        Thread1 a = new Thread1();
        new Thread(()->{
            System.out.println("这是第二个线程");
        },"p2").start();
        new Thread(a,"p2").start();
    }
}
