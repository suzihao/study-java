package com.company.leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode2200 {


    public static List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        List<Integer> res = new ArrayList<>();
        Map<Integer,Integer> keyMap = new HashMap<>();
        for(int i =0;i<nums.length;i++){
            if(nums[i] == key){
                keyMap.put(i,key);
            }
        }
        if(keyMap.isEmpty()){
            return res;
        }
        for(int i =0;i<nums.length;i++){
            for(Integer keyIndex : keyMap.keySet()){
                if(Math.abs(i-keyIndex) <= k){
                    res.add(i);
                    break;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(findKDistantIndices(new int[]{3,4,9,1,3,9,5},9,1));
        System.out.println(findKDistantIndices(new int[]{2,2,2,2,2},2,2));
    }

}
