package com.company.concurrent.c_025;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 队列
 * Queue
 * --ConcurrentLinkedQueue
 * --BlockingQueue
 * ----LinkedBQ
 * ----ArrayBQ
 * ----TransferQueue
 * ----SynchronusQueue
 * --DelayQueue 执行定时任务
 */
public class T04_ConcurrentQueue {

    public static void main(String[] args) {
        Queue<String> queue = new ConcurrentLinkedQueue<>();

        for (int i = 0; i < 10; i++) {
            //类似add 若有容量限制add会抛异常，offer返回bool
            queue.offer("a" + i);
        }

        System.out.println(queue);
        System.out.println(queue.size());

        //真正取出（且删除）add-remove
        System.out.println(queue.poll());
        System.out.println(queue.size());

        //拿出但仍在 element抛异常
        System.out.println(queue.peek());
        System.out.println(queue.size());

        //双端队列Deque
    }

}
