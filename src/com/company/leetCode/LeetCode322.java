package com.company.leetCode;

/**
 * @author 苏和
 * @version 2020/5/6
 */
public class LeetCode322 {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        for(int coin : coins) {
            for(int i = coin; i <= amount; i++) {
                dp[i] = dp[i] + dp[i - coin];
            }
        }

        return dp[amount];
    }

    public static void main(String[] args) {

    }
}
