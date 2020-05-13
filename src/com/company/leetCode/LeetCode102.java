package com.company.leetCode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author 苏和
 * @version 2020/5/13
 */
public class LeetCode102 {

      public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    public static List<List<Integer>> levelOrder(TreeNode root) {
          List<List<Integer>> res = new LinkedList<>();
          if (root ==null) return res;
          Queue<TreeNode> temps = new LinkedList<>();
          TreeNode temp;
          Queue<TreeNode> queue = new LinkedList();
          queue.add(root);
          while (!queue.isEmpty()){
              List<Integer> r = new LinkedList<>();
              int a = queue.size();

              for (int i=0;i<a;i++){
                  temps.offer(queue.poll());
              }
              int b =temps.size();
              for (int i=0;i<b;i++){
                  temp = temps.poll();
                  if (temp.left!=null) queue.offer(temp.left);
                  if (temp.right!=null) queue.offer(temp.right);
                  r.add(temp.val);
              }
              res.add(r);
          }
          return res;
    }

    public static void main(String[] args) {
          TreeNode root = new TreeNode(3);
          TreeNode r1 = new TreeNode(9);
          TreeNode r2 = new TreeNode(20);
        TreeNode r3 = new TreeNode(15);
        TreeNode r4 = new TreeNode(7);
        r2.left = r3;
        r2.right = r4;
        root.left = r1;
        root.right=r2;
        System.out.println(levelOrder(null));



    }
}
