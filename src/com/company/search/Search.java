package com.company.search;

import java.util.*;

public class Search {

    /**
     * 简单查找
     * @param a
     * @param num
     * @return
     */
    public static int search(int[] a,int num){
        for (int i=0;i<a.length;i++){
            if (a[i] == num) return i;

        }
        return -1;
    }

    /**
     * 二分查找
     * @param a
     * @param num
     * @return
     */
    public static int binarySearch(int[] a,int num){
        if (a.length == 0) return -1;
        int startPos = 0;
        int endPos = a.length-1;
        int m = (startPos + endPos) / 2;
        while (startPos <= endPos){
            if (num == a[m]) return m;
            if (num > a[m]) startPos = m+1;
            if (num < a[m]) endPos =m -1;
            m = (startPos +endPos ) / 2;
        }
        return -1;
    }
    /**
     * 广度优先搜索指出是否有A到B的路径，如果有，广度优先搜索将找出最短路径。
     * 例如：高德地图中从A点到B点之间换乘次数最少的路径。
     * 面临类似与寻找最短路径的问题时，可尝试使用图来建立模型。
     * 有向图的边为箭头，无向图中的关系为双向的。树是一种特殊的图。
     * @param
     * @param
     * @return
     */
    public static boolean breadthFirstSearch(Map m){
        Queue q = new LinkedList();
        q.addAll((Collection) m);
        while (!q.isEmpty()){
            int o = (int) q.remove();
            if(o == 0) {
                System.out.println(o);
                return true;
            }else {
                q.addAll((Collection) m.get(o));
            }
        }
        return false;
    }

    /**
     * 狄克斯特拉算法，找出加权图中的最短路径。只适用于有向无环图。
     * 不适用与有负权的算法，贝尔曼福德算法可以解决有负权的问题。
     * graph:{start:{a:6,b:2},a:{end:1},b{a:3,end:5},end:-}
     * costs:{a:6,b:2,end:-}
     * parents:{a:start,b:start,end:-}
     * @param
     * @param
     * @return
     */

}
