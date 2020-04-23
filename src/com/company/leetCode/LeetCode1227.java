package com.company.leetCode;

/**
 * @author 苏和
 * @version 2020/4/23
 */
public class LeetCode1227 {
    public double nthPersonGetsNthSeat(int n) {
        /*
        因为我们不知道乘客对应哪个座位
        因此我们直接按入场顺序给它们的座位编号
        1 号乘客是 1 号座位，(ps：1 号 票丢了，并不知道自己坐 1 号座位)
        2 号乘客 是 2 号座位
        。。。

        我们要求的是最后一位乘客 n 最终能坐到自己座位，即 n 号座位的概率
        因为 1 号 票丢了，因此分析 1 号 坐不同座位的情况，它有 3 种选择
        1、1 号有 1 / n 的概率 坐到 1 号座位，那么不会对 [2, n] 号乘客产生影响，因为它们都有票，那么 n 号乘客坐到自己座位的概率为 1
        2、1 号有 1 / n 的概率 坐到 n 号座位，那么 n 号 肯定坐不到 n 号座位，那么概率为 0
        3、1 号有 n - 2 / n 的概率 坐到 [2, n - 1] 号座位，假设是 k 号座位，
        那么对于 k 号乘客来说它有 3 种选择
        1、k 号有 1 / n - 1 的概率 坐到 1 号座位，那么不会对其他乘客产生影响，那么 n 号乘客坐到自己座位的概率为 1
                    （为什么分母是 n - 1？ 因为 k 号座位被坐了，它必定不会去坐）
        2、k 号有 1 / n - 1 的概率 坐到 n 号座位，那么 n 号 肯定坐不到 n 号座位，那么概率为 0
        3、k 号有 n - 3 / n - 1 的概率坐到 除 1、k、n 外的座位，假设是 m 号座位
        。。。
        递归分析

        当 n = 1 时，那么概率必定为 1
        */
        if(n == 1){
            return 1;
        }
        //注意不能直接写 1 / n, 否则结果直接为 0
        return 1.0 / n + (double)(n - 2) / n * nthPersonGetsNthSeat(n - 1);
    }

    public double nthPersonGetsNthSeat1(int n) {

        double[] f = new double[n + 1];
        f[1] = 1;
        for(int i = 2; i <= n; i++){
            f[i] = 1.0 / i + (double)(i - 2) / i * f[i - 1];
        }
        return f[n];
    }

    public double nthPersonGetsNthSeat2(int n) {
        return n == 1 ? 1 : 0.5;
    }
}
