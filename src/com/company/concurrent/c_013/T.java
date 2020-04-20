package com.company.concurrent.c_013;

import java.util.ArrayList;
import java.util.List;

/**
 * （但其不能保证多个线程共同修改变量时带来的不一致问题，即其不能替代synchronized）
 * 只保证可见性，不保证原子性
 */
public class T {
    volatile int count = 0;

    void m() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public static void main(String[] args) {
        T t = new T();
        List<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(() -> t.m(), "thread-" + i));
        }

        threads.forEach((o) -> o.start());

        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(t.count);
    }

}
