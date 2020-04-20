package com.company.concurrent.suzihao;

public class T1 {
    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            System.out.println(i);
        }

        for (int i=0;i<10;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName());
            },"t"+i).start();
        }

    }
}
