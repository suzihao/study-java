package com.company.concurrent.c_007;

import java.util.concurrent.TimeUnit;

public class Accout {
    String name;
    double balance;

    public synchronized void set(String name, double balance) {
        this.name = name;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public /*synchronized*/ double getBalance(String name) {
        return this.balance;
    }

    //脏读：原因只对写加锁，未对读加锁。可对getBalance也加锁解决
    public static void main(String[] args) {
        Accout a = new Accout();
        new Thread(() -> a.set("long", 100.0)).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a.getBalance("long"));

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a.getBalance("long"));
    }

}
