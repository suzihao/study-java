package com.company.interview;

import java.util.*;

public class Interview3 {

    // 用户 商品 单价 获取每个用户，单价最高的所有记录 实现sql 使用子查询

    // 一个方法 获取字符串里第一个出现的最多字符
    public static char getChar(String s){
        if (s == null || s.isEmpty()){
            throw new RuntimeException("字符串为空");
        }
        Map<Character,Integer> countMap = new HashMap<>();
        Set<Character> char1 = new LinkedHashSet<>();
        // 统计次数
        for (int i =0;i <s.length(); i++){
            char currentChar = s.charAt(i);
            countMap.put(currentChar,countMap.getOrDefault(currentChar,0)+1);
            char1.add(currentChar);
        }
        int maxCount = 0;
        for (int count : countMap.values()){
            if (count > maxCount){
                maxCount = count;
            }
        }

        for (Character currentChar:char1){

            if (countMap.get(currentChar) == maxCount){
                return currentChar;
            }
        }

        return 'a';
    }

    public static void main(String[] args) {
        String a = "aaabbbb";
        char result = getChar(a);
        System.out.println(result);
    }
}
