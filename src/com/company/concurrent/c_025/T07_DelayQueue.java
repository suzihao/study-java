package com.company.concurrent.c_025;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * DQ 无界队列 只有等一段时间后才可以消费
 */
public class T07_DelayQueue {

    static BlockingQueue<MyTask> queue = new DelayQueue<>();
    static Random r = new Random();

    static class MyTask implements Delayed {
        long runningTime;

        MyTask(long rt) {
            this.runningTime = rt;
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
                return -1;
            } else if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
                return 1;
            } else {
                return 0;
            }
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public String toString() {
            return String.valueOf(runningTime);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        MyTask t1 = new MyTask(now + 1000);
        MyTask t2 = new MyTask(now + 2000);
        MyTask t3 = new MyTask(now + 3000);
        MyTask t4 = new MyTask(now + 4000);
        MyTask t5 = new MyTask(now + 5000);

        queue.put(t1);
        queue.put(t2);
        queue.put(t3);
        queue.put(t4);
        queue.put(t5);

        System.out.println(queue);

        for (int i = 0; i < 5; i++) {
            System.out.println(queue.take());
        }

    }
}
