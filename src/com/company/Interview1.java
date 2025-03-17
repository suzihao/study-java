package com.company;

import java.util.*;

/**
 * @author suhe17
 * @since 2025/3/17
 */
public class Interview1 {

    static class TreeNode{
        int val;
        TreeNode left,right;
        TreeNode(int x){
            val = x;
        }
    }

    public static void trans(TreeNode root){
        TreeNode curr = root;
        while (curr != null){
            if (curr.left != null){
                TreeNode pre = curr.left;
                while (pre.right != null){
                    pre = pre.right;
                }
                pre.right = curr.right;
                curr.right = curr.left;
                curr.left = null;
            }
            curr = curr.right;
        }

    }

    public static void print(TreeNode root){
        while (root != null){
            System.out.println(root.val);
            root = root.right;
        }
    }

    public static int lengthArr(int[] nums){
        List<Integer> sub = new ArrayList<>();
        for (int num:nums){
            if (sub.isEmpty() || sub.get(sub.size() - 1) < num){
                sub.add(num);
            }else {
                int i = binary(sub,num);
                sub.set(i,num);
            }
        }
        return sub.size();

    }

    private static int binary(List<Integer> sub,int key){
        int left = 0;
        int right = sub.size() -1;
        while (left <=right){
            int mid = left + (right - left )/2;
            if (sub.get(mid) < key){
                left = mid +1;
            }else if(sub.get(mid) > key){
                right = mid -1;
            }else {
                return mid;
            }
        }
        return left;

    }

    public static void main(String[] args) {
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(5);
//        root.left.left = new TreeNode(3);
//        root.left.right = new TreeNode(4);
//        root.right.right = new TreeNode(6);
//
//        trans(root);
//        print(root);
        int[] nums = {10,9,2,2,3,7,101,18};


        System.out.println(lengthArr(nums));
    }


}
