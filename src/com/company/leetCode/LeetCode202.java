package com.company.leetCode;

/**
 * @author 苏和
 * @version 2020/4/30
 */
public class LeetCode202 {
    public int squareSum(int n) {
        int sum = 0;
        while(n > 0){
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }

    public boolean isHappy(int n) {
        int slow = n, fast = squareSum(n);
        while (slow != fast){
            slow = squareSum(slow);
            fast = squareSum(squareSum(fast));
        };
        return slow == 1;
    }

    public static void main(String[] args) {
         int n=1;
        System.out.println(233/ 10);

    }
}
