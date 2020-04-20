package com.company.concurrent.c_025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 静态方法 加锁
 */
public class T03_SynchronizedList {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        List<String> listSyc = Collections.synchronizedList(list);
    }

}
