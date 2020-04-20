package com.company.concurrent.c_026;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPoolExecutor 大多数背后的实际,除ForkJoinPool     自定义
 */
public class T13_ThreadPoolExecutor {

    public static void main(String[] args) {
        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(
                        8,
                        9,
                        60,
                        TimeUnit.SECONDS,
                        new LinkedBlockingQueue<>());

    }

}
