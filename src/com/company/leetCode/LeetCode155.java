package com.company.leetCode;

import java.util.List;

/**
 * @author 苏和
 * @version 2020/5/12
 */
public class LeetCode155 {

    static class MinStack {
        int[] arr = null;
        private int min;
        private int n = 0;

        /** initialize your data structure here. */
        public MinStack() {
            arr = new int[4];
            min = Integer.MAX_VALUE;
        }

        public void push(int x) {
            if(n == arr.length)
                resize(2 * n);
            arr[n++] = x;
            if(x < min)
                min = x;
        }

        public void pop() {
            if(arr.length > 4 && n <= arr.length / 4)
                resize(arr.length / 2);
            if(arr[n - 1] == min){ // 如果要删除的元素是最小元素 要更改记录的min
                int temp = Integer.MAX_VALUE;
                for(int i = 0; i < n - 1; i++){
                    if(arr[i] < temp)
                        temp = arr[i];
                }
                min = temp;
            }
            n--;
        }

        public int top() {
            return arr[n - 1];
        }

        public int getMin() {
            return min;
        }

        private void resize(int newSize){
            int[] temp = new int[newSize];
            for(int i = 0; i < arr.length; i++)
                temp[i] = arr[i];
            this.arr = temp;
        }
    }

    public static void main(String[] args) {

        MinStack m = new MinStack();

    }

}
