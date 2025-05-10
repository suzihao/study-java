package com.company.interview;

import java.util.Scanner;

public class RedBook {


    // 塔子哥有n个账号，每个账号粉丝数为ai 。这天他又创建了一个新账号，他希望新账号的粉丝数恰好等于x。为此他可以向自己已有账号的粉丝们推荐自己的新账号，这样以来新账号就得到了之前粉丝的关注。
    //他想知道，他最少需要在几个旧账号发“推荐新账号”的文章，可以使得他的新账号粉丝数恰好为x，除此以外，他可以最多从中选择一个账号多次发“推荐新账号”的文章。
    //假设一个旧账号粉丝数为ai，如果仅推荐一次，那么新账号粉丝数增加⌊ai/2⌋，如果多以推荐，则粉丝数增加ai
    //————————————————
    //输入包含2行。
    //第一行两个正整数n,x(1≤n,x≤100)，分别表示塔子哥的旧账号个数，和新账号想要的粉丝数。
    //第二行n个正整数𝑎𝑖 (1≤ai≤100)，表示塔子哥每个旧账号的粉丝数。
    // 样例1
    //输入
    //5 8
    //1 2 3 4 10
    //
    //输出
    //2
    public static void main(String[] args) {
        final int INF = 0x3f3f3f3f; // 约1e9，表示无穷大

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 旧账号数量
        int m = sc.nextInt(); // 目标粉丝数上限

        // 初始化旧账号粉丝数数组（索引从1到n）
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
        }

        // 动态规划数组：dp[i][0/1] 表示粉丝数不超过i时的最小推荐数
        int[][] dp = new int[110][2]; // 最大支持m=109

        // 初始化所有值为INF（除初始状态）
        for (int i = 0; i < 110; i++) {
            for (int j = 0; j < 2; j++) {
                dp[i][j] = INF;
            }
        }
        dp[0][0] = 0; // 粉丝数0时无需推荐
        dp[0][1] = 0; // 粉丝数0时也无需推荐

        // 遍历每个旧账号
        for (int i = 1; i <= n; i++) {
            // 情况1：当前账号仅推荐一次（占用a[i]/2的容量）
            for (int j = Math.min(m, 109); j >= a[i] / 2; j--) {
                if (dp[j - a[i]/2][0] + 1 < dp[j][0]) {
                    dp[j][0] = dp[j - a[i]/2][0] + 1;
                }
                if (dp[j - a[i]/2][1] + 1 < dp[j][1]) {
                    dp[j][1] = dp[j - a[i]/2][1] + 1;
                }
            }

            // 情况2：当前账号推荐多次（占用a[i]的容量）
            for (int j = Math.min(m, 109); j >= a[i]; j--) {
                if (dp[j - a[i]][0] + 1 < dp[j][1]) {
                    dp[j][1] = dp[j - a[i]][0] + 1;
                }
            }
        }

        // 输出结果（若无法达到则返回-1）
        int result = Math.min(dp[m][0], dp[m][1]);

        // 打印dp数组
        for(int i =0;i< dp.length;i++){
            for (int j=0;j<dp[0].length;j++){
                System.out.print(dp[i][j] + " ");
            }
            System.out.println(); // 每行结束后换行
        }
        System.out.println(result != INF ? result : -1);
    }


}
