package com.company.thread;

public class Thread2 extends Thread{

    @Override
    public void run() {
        super.run();
        System.out.println("这个线程继承了Thread");
    }

    public static void main(String[] args) {
        new Thread2().start();
    }
}
