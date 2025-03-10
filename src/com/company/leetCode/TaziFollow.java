package com.company.leetCode;

import java.util.Arrays;

/**
 * 塔子哥有n个账号，每个账号粉丝数为ai 。这天他又创建了一个新账号，他希望新账号的粉丝数恰好等于x。为此他可以向自己已有账号的粉丝们推荐自己的新账号，这样以来新账号就得到了之前粉丝的关注。
 * 他想知道，他最少需要在几个旧账号发“推荐新账号”的文章，可以使得他的新账号粉丝数恰好为x，除此以外，他可以最多从中选择一个账号多次发“推荐新账号”的文章。
 * 假设一个旧账号粉丝数为ai，如果仅推荐一次，那么新账号粉丝数增加⌊ai/2⌋，如果多以推荐，则粉丝数增加ai
 * 输入包含2行。
 * 第一行两个正整数n,x(1≤n,x≤100)，分别表示塔子哥的旧账号个数，和新账号想要的粉丝数。
 * 第二行n个正整数𝑎𝑖 (1≤ai≤100)，表示塔子哥每个旧账号的粉丝数。
 * 输出包含一行一个整数，表示塔子哥最少需要向多少个旧帐号推荐新账号，如果无法做到，输出-1
 * 样例1
 * 输入
 * 5 8
 * 1 2 3 4 10
 * @author suhe17
 * @since 2025/3/8
 */
public class TaziFollow {

    public static int INF = 0x3f3f3f3f;

    /**
     * 计算最少需要推荐新账号的旧账号数量
     * @param n 旧账号个数
     * @param x 新账号粉丝数
     * @param a 每个账号的粉丝数
     * @return int 最少需要的旧账号数量，如果无法达成目标则返回-1
     */
    public static int follow(int n, int x, int[] a) {
        final int INF = Integer.MAX_VALUE / 2; // 使用较大的值表示不可达的状态
        int[][] dp = new int[x + 1][2];

        // 初始化dp数组
        for (int i = 0; i <= x; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][0] = dp[0][1] = 0; // 粉丝数是0的情况，不需要向任何旧账户推荐，因此初始化为0

        // 动态规划填表
        for (int i = 0; i < n; i++) {
            // 先考虑单次推荐的情况
            for (int j = x; j >= a[i] / 2; j--) {
                dp[j][0] = Math.min(dp[j][0], dp[Math.max(j - a[i] / 2, 0)][0] + 1);
                dp[j][1] = Math.min(dp[j][1], dp[Math.max(j - a[i] / 2, 0)][1] + 1);
            }

            // 再考虑多次推荐的情况
            for (int j = x; j >= a[i]; j--) {
                dp[j][1] = Math.min(dp[j][1], dp[Math.max(j - a[i], 0)][0] + 1);
            }
        }

        // 返回结果
        if (dp[x][0] != INF || dp[x][1] != INF) {
            return Math.min(dp[x][0], dp[x][1]);
        } else {
            return -1;
        }
    }
//
//    public static int follow(int n, int x, int[] a) {
//        final int INF = Integer.MAX_VALUE / 2; // 使用较大的值表示不可达的状态
//        int result = INF;
//
//        // 枚举哪个账号会被多次推荐
//        for (int i = 0; i < n; i++) {
//            int currentX = x;
//            int count = 0;
//
//            // 如果选择第i个账号进行多次推荐
//            if (a[i] <= currentX) {
//                currentX -= a[i];
//                count++;
//            } else {
//                continue; // 如果多次推荐都无法使currentX减少，则跳过
//            }
//
//            // 对剩下的账号进行单次推荐
//            Arrays.sort(a);
//            for (int j = 0; j < n && currentX > 0; j++) {
//                if (j != i) { // 不是已经被多次推荐的账号
//                    currentX -= a[j] / 2;
//                    count++;
//                }
//            }
//
//            // 如果当前方案能满足需求，则更新结果
//            if (currentX <= 0) {
//                result = Math.min(result, count);
//            }
//        }
//
//        // 如果result仍为INF，说明没有找到可行方案
//        return result == INF ? -1 : result;
//    }


    public static void main(String[] args) {
        int a = follow(5,8,new int[]{1,2,3,4,50});
        System.out.println(a);
    }
}
