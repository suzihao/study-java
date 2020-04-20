package com.company.sort;

public class BubbleSort {

    public static void bubbleSort(int[] nums){
        int temp;
        for (int j = nums.length - 1;j >= 0;j--){
            for (int i = 0;i < j;i++){
                if (nums[i] > nums[i+1]){
                    temp = nums[i];
                    nums[i] = nums[i+1];
                    nums[i+1] = temp;
                }
            }
        }
    }
}
