package com.company.leetCode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author 苏和
 * @version 2020/4/22
 */
public class LeetCode199 {

    public static class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
  }

    public static List<Integer> rightSideView(TreeNode root) {
        int cnt=0,tmp=0;
        TreeNode node;
        List<Integer> res = new LinkedList<>();
        if(root==null){
            return res;
        }
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            cnt=queue.size();
            while(cnt>0){
                node=queue.poll();
                if(node.left!=null)queue.offer(node.left);
                if(node.right!=null)queue.offer(node.right);
                cnt--;
                tmp=node.val;
            }
            res.add(tmp);
        }
        return res;

    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode(2);
        a.right= new TreeNode(5);
        TreeNode b = new TreeNode(3);
        b.right = new TreeNode(4);
        TreeNode c =new TreeNode(1);
        c.left = a;
        c.right = b;
        System.out.println(rightSideView(c));

    }


}
