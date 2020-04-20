package com.company.concurrent.c_018;

/**
 * 不要以字符串常量作为锁定对象
 * 在下面的例子中，m1 m2锁定的其实是同一个对象
 * 这种情况还会发生比较诡异的现象，比如你用到了一个类库，在该类库中代码锁定了字符串“hello”
 * 但是你读不到源码，所以你在自己的代码中也锁定了“hello”，这时可能发生诡异的死锁阻塞
 * 因为你的程序和你用到的类库不经意间使用了同一把锁
 */
public class T {
    String s1 = "hello";
    String s2 = "hello";

    void m1() {
        synchronized (s1) {

        }
    }

    void m2() {
        synchronized (s2) {

        }
    }

}
