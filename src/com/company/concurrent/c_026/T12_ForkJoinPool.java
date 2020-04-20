package com.company.concurrent.c_026;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * fork join 计算百万个数的和
 */
public class T12_ForkJoinPool {

    static int[] nums = new int[1000000];
    static final int MAX_NUM = 50000;
    static Random r = new Random();

    static {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextInt(100);
        }
        //stream计算
        System.out.println(Arrays.stream(nums).sum());
    }

    //分治计算：实现ForkJoinTask接口，或其子接口（RecursiveAction无返回值 RecursiveTask有返回值）
    /*static class AddTask extends RecursiveAction {
        int start, end;

        AddTask(int s, int e) {
            this.start = s;
            this.end = e;
        }

        @Override
        protected void compute() {
            if ((end - start) <= MAX_NUM) {
                long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                System.out.println(String.join(" ", "from", String.valueOf(start), "to", String.valueOf(end), "=", String.valueOf(sum)));
            } else {
                int middle = start + (end - start) / 2;
                AddTask subTask1 = new AddTask(start, middle);
                AddTask subTask2 = new AddTask(middle, end);
                subTask1.fork();
                subTask2.fork();
            }

        }
    }*/


    static class AddTask extends RecursiveTask<Long> {
        int start, end;

        public AddTask(int s, int e) {
            this.start = s;
            this.end = e;
        }

        @Override
        protected Long compute() {
            if ((end - start) <= MAX_NUM) {
                long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                return sum;
            } else {
                int middle = start + (end - start) / 2;
                AddTask subTask1 = new AddTask(start, middle);
                AddTask subTask2 = new AddTask(middle, end);
                subTask1.fork();
                subTask2.fork();

                return subTask1.join() + subTask2.join();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ForkJoinPool fjp = new ForkJoinPool();
        AddTask task = new AddTask(0, nums.length);
        fjp.execute(task);

        //有返回值的情况
        long result = task.join();
        System.out.println(result);

        //Daemon线程阻塞
        System.in.read();
    }
}
