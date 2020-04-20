package com.company.concurrent.c_005;

/**
 * synchronized关键字： 对某个对象加锁
 */
public class T implements Runnable{

    private int count = 10;

    @Override
    public /*synchronized*/ void run() {
        // 不加锁存在线程重入
        count--;
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //重入后导致该处上下代码无法保证原子性
        System.out.println(Thread.currentThread().getName() + " count=" + count);
    }

    public static void main(String[] args) {
        T t = new T();
        for(int i = 0;i<5;i++){
            new Thread(t,"Thread"+i).start();
        }
    }

}
