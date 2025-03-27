package com.company.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ABCPrinter {
    private static final int TOTAL = 200 * 3; // 每个字母打印200次，总次数600次
    private static int current = 0; // 当前轮次
    private static final Lock lock = new ReentrantLock();
    private static final Condition condA = lock.newCondition();
    private static final Condition condB = lock.newCondition();
    private static final Condition condC = lock.newCondition();

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            while (current < TOTAL) {
                lock.lock();
                try {
                    // 等待轮到自己（current %3 ==0）
                    while (current % 3 != 0 && current < TOTAL) {
                        condA.await();
                    }
                    if (current >= TOTAL) break;
                    System.out.print("A");
                    current++;
                    condB.signal(); // 唤醒线程B
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread threadB = new Thread(() -> {
            while (current < TOTAL) {
                lock.lock();
                try {
                    // 等待轮到自己（current %3 ==1）
                    while (current % 3 != 1 && current < TOTAL) {
                        condB.await();
                    }
                    if (current >= TOTAL) break;
                    System.out.print("B");
                    current++;
                    condC.signal(); // 唤醒线程C
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread threadC = new Thread(() -> {
            while (current < TOTAL) {
                lock.lock();
                try {
                    // 等待轮到自己（current %3 ==2）
                    while (current % 3 != 2 && current < TOTAL) {
                        condC.await();
                    }
                    if (current >= TOTAL) break;
                    System.out.print("C");
                    current++;
                    condA.signal(); // 唤醒线程A
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
    }
}
