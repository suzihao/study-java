package com.company.concurrent.c_026;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池  newFixedThreadPool固定数量
 */
public class T05_ThreadPool {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 6; i++) {
            service.execute(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        System.out.println(service);

        service.shutdown();
        //任务是否执行完
        System.out.println(service.isTerminated());
        //是否关闭
        System.out.println(service.isShutdown());
        System.out.println(service);

        TimeUnit.SECONDS.sleep(5);

        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());
        System.out.println(service);

    }

}
