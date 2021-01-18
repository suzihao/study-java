package com.company.sort;

public class SelectionSort {

    public static void main(String[] args) {
        int[] nums = {2,9,3,5,6,7,8,4,1};
        for (int i = 0;i < nums.length;i++){
            System.out.print(nums[i]);
        }
        System.out.println();
        long start = System.currentTimeMillis();
//        selectionSort(nums);
//        BubbleSort.bubbleSort(nums);

        QuickSort.quickSort(nums,0,nums.length-1);
        long end = System.currentTimeMillis();
        for (int i = 0;i < nums.length;i++){
            System.out.print(nums[i]);
        }
        System.out.println(end - start);


    }

    public static void selectionSort(int[] nums){
        int temp,k;
        for (int i = 0;i < nums.length;i++){
            k = i;
            for (int j = i + 1;j < nums.length;j++){
                if (nums[j] < nums[k]) k=j;
            }
            if (k != i){
                temp = nums[i];
                nums[i] = nums[k];
                nums[k] = temp;
            }
        }
    }
}
