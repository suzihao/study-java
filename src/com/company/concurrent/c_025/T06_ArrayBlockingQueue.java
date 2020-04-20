package com.company.concurrent.c_025;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class T06_ArrayBlockingQueue {

    //有界队列
    static BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            queue.put("a" + i);
        }

        //满了会阻塞
        queue.put("aaa");
        //满了报异常
        //queue.add("aaa");

        //返回值bool
        //queue.offer("aaa");
        boolean bool = queue.offer("aaa", 1, TimeUnit.SECONDS);
        System.out.println(bool);

        System.out.println(queue);

    }
}
