package com.company.concurrent.c_026;

import java.util.concurrent.Executors;

/**
 * 认识Executors 操作executor的工具类
 */
public class T04_Executors {

    public static void main(String[] args) {
        Executors executors = (Executors) Executors.newCachedThreadPool();
        //...
    }

}
