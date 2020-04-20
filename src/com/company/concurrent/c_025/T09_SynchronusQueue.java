package com.company.concurrent.c_025;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 容量为0的特殊transfer队列：来了必须消费掉
 */
public class T09_SynchronusQueue {

    public static void main(String[] args) throws InterruptedException {
        //容量为0！
        BlockingQueue<String> queue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        //满则阻塞等待消费
        queue.put("aaa");
        //error
        //queue.add("aaa");
        System.out.println(queue.size());
    }
}
