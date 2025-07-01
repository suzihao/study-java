package com.company.interview;

import java.util.Arrays;

public class AliInterview3 {

    // 高德打车
//    优惠券表：coupon (id, name, amount, inventory,version):
//    用户优惠券表：user_coupon (id, user_id, coupon_id, coupon_name, coupon_amount):
//
//    领取优惠券时的事务实现：
//    update coupon: invertory - 1;
//    insert user_coupon


    public static int[] maxSubsequence(int[] nums, int k) {
        // 创建下标数组，对下标数组排序
        Integer[] idx = new Integer[nums.length];
        Arrays.setAll(idx, i -> i);
        Arrays.sort(idx, (i, j) -> nums[j] - nums[i]);

        // 对前 k 个下标排序
        // 注：排序 int[] 比排序 Integer[] 快 2ms
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = idx[i];
        }
        Arrays.sort(ans);

        // 取出 nums 的子序列
        for (int i = 0; i < k; i++) {
            ans[i] = nums[ans[i]];
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSubsequence(new int[]{2, 1, 3, 3}, 2)));
    }
}
