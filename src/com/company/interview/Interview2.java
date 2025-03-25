package com.company.interview;

import java.util.LinkedList;
import java.util.Queue;
//数据
public class Interview2 {

    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    // 深度 两种方法实现

    // DFS
    public static int depthDFS(TreeNode head){
        if (head == null){
            return 0;
        }
        int leftDepth = depthDFS(head.left);
        int rightDepth = depthDFS(head.right);
        return Math.max(leftDepth,rightDepth) +1;

    }

    // BFS
    public static int depthBFS(TreeNode head){
        if (head == null){
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        int depth = 0;

        while (!queue.isEmpty()){
            int level = queue.size();
            depth++;
            for (int i=0;i<level;i++){
                TreeNode node = queue.poll();
                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right !=null){
                    queue.add(node.right);
                }
            }

        }
        return depth;

    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.right.left = new TreeNode(5);
        head.right.right = new TreeNode(6);
        System.out.println("树高="+ depthDFS(head));

        String a = "abc";
        int path = a.charAt(1) - 'a';
        System.out.println(path);
    }
}
