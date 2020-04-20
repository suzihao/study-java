package com.company.concurrent.c_001;

/**
 * synchronized关键字： 对某个对象加锁
 */
public class T {

    private int count = 10;
    private Object o = new Object();

    public void m() {
        synchronized (o) {
            //任何线程要执行下面的代码，必须先拿到o的锁
            count--;
            System.out.println(Thread.currentThread().getName() + " count=" + count);
        }
    }

    public static void main(String[] args) {
        T t = new T();
        for (int i=1;i<11;i++){
            new Thread(t::m,"t"+i).start();
        }

    }

}
