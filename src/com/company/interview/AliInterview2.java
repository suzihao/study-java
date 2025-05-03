package com.company.interview;

import java.util.Arrays;

public class AliInterview2 {

//    一张学生成绩表(t1)，它有2个字段：id（int，主键）、score(int)。写一条SQL找出大于该表平均分的id
//
//    select id from table where score > （平均分 select avg(score) from table）
//
//
//    请你为一家餐厅设计一个订单管理系统，列出相关的模型及属性
//（介绍会涉及到哪些表、分别有哪些必要的字段）
//    菜单表
//            sku_id
//    sku_name
//            sku_img
//    sku_desc
//            price
//    creat_time
//            用户表
//    user_id
//            creat_time
//    订单表
//            order_id
//    total_price
//            user_id
//    creat_time
//            订单明细表
//    order_id
//            sku_id
//    qty
//            price
//    creat_time
//
//            购物车
//
//
//    题目描述
//    小红希望组建若干团队，一共有n个人，每个人希望自己所在团队的人数不少于aᵢ人，
//    请问再满足所有参加团队成员的要求的情况下（不参加的可以不满足），小红最多可以组建多少个团队。
//    不一定n个人都要参加团队，也可以有人不参加团队。
//    输入描述
//    第一行输入一个整数n，表示n个人
//    第二行输入n个整数，第i个整数表示aᵢ。
//            1 <= n <= 100000
//            1 <=  aᵢ <= n
//            输出描述
//    输出一个整数，表示小红最多可以组建多少个团队。
//    补充说明
//            示例1
//    输入：5
//            1 1 2 2 3
//    输出：3
//    说明：
//    第一个人在第一个团队
//            第二个人在第二个团队
//    第三、四个人在第三个团队，或者第三、四、五在同一个团队
//    // dp[i] 当有i个人的时候 最多可以组建多少个团队
//// dp[i] = dp[i-1]
//// 能否满足ai
//    int team(int n, int[] array){
//        int[] dp = new int[n+1];
//        for(int i=;i<=n;i++){
//            if(array[i] >i){
//
//            }else{
//
//            }
//        }
//
//
//        retrun dp[n];
//    }
    // spring 和 spring boot 有什么区别
    // 贪心算法
    public static int team(int n,int[] array){
        Arrays.sort(array);// 从小到大排序
        int teamCount = 0;
        int currentTeamSize = 0;
        for(int i = 0;i<n;i++){
            currentTeamSize++;
            if(currentTeamSize >= array[i]){
                teamCount++;
                currentTeamSize =0;
            }
        }
        return teamCount;
    }

    public static void main(String[] args) {
        int[] a = {1,1,2,2,3};
        int res = team(5,a);
        System.out.println(res);
    }


}
