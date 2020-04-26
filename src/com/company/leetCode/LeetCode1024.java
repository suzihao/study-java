package com.company.leetCode;

import java.util.Arrays;

/**
 * @author 苏和
 * @version 2020/4/23
 */
public class LeetCode1024 {

    public static int videoStitching(int[][] clips, int T) {
        int[] dp = new int[T+1];
        int start = 0;
        int end = 0;
        Arrays.sort(clips,(o1,o2)->{
            if (o1[0]==o2[0]) return o1[1]-o2[1];
            return o1[0]-o2[0];
        });

        for (int [] clip:clips) {
            end = clip[1];
        }
        return dp[T];
    }

    public static void main(String[] args) {
        System.out.println(1%2);
//        videoSti tching(new int[][]{{0,2},{4,6},{8,10},{1,9},{1,5},{5,9}},10);
    }

}
