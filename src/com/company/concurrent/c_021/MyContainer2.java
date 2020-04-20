package com.company.concurrent.c_021;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 写一个固定容量的同步容器，拥有put get getCount方法
 * 能够支持两个生产者线程以及10个消费者线程的阻塞调用
 * <p>
 * -使用wait 和 notify/notifyAll来实现
 * <p>
 * +使用Lock和Condition来实现
 * condition方式可以更加精确的指定哪些线程被唤醒
 */
public class MyContainer2<T> {
    final private LinkedList<T> lists = new LinkedList<>();
    //最多十个元素
    final private int MAX = 10;
    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public void put(T t) {
        lock.lock();
        try {
            //为什么是while不是if:wait释放锁后回来重新判断while
            while (lists.size() == MAX) {
                producer.await();
            }
            lists.add(t);
            ++count;
            //通知消费者线程进行消费
            consumer.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public synchronized T get() {
        T t = null;
        lock.lock();
        try {
            while (lists.size() == 0) {
                consumer.await();
            }
            t = lists.removeFirst();
            count--;
            //通知生产者进行生产
            //只写notify唤醒的可能唤醒p
            producer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {
        MyContainer2<String> c = new MyContainer2<>();
        //启动消费者线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(c.get());
                }
            }, "c " + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //启动生产者线程
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    c.put(Thread.currentThread().getName() + " " + j);
                }
            }, "p " + i).start();
        }
    }

}
