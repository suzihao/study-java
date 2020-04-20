package com.company.concurrent.c_012;

/**
 * volatile 关键字，使得一个变量子啊多个线程间可见
 * AB线程共用一个变量，java默认是A线程拷贝有一份，这样如果B线程做了修改，线程A未必知道
 * 使用volatile 使得所有线程搜会读到该变量的修改值
 *
 * （但其不能保证多个线程共同修改变量时带来的不一致问题，即其不能替代synchronized）
 */
public class T {
    //对比有无volatile的情况下的区别
    /*volatile*/ boolean running = true;

    void m() {
        System.out.println("start");
        while (running) {

        }
        System.out.println("end");
    }

    public static void main(String[] args) {
        T t = new T();
        new Thread(() -> t.m(), "t1").start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.running = false;
    }

}
