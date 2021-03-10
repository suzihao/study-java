package com.company.calculate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author suhe17@jd.com
 * @date 2021/3/10
 * @description
 **/
public class SetToList {
    public static void main(String[] args) {
        Set set = new HashSet();
        for (int i =0;i < 100;i++){
            set.add(i);
        }
        long start = System.currentTimeMillis();
        List list = new ArrayList<>(set);
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
    }
}
