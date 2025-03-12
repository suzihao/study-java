package com.company.leetCode.principle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 二叉树公共祖先
 * @author suhe17
 * @since 2025/3/11
 */
public class BinaryTreeLCA {

   static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    /**
     * 查找给定节点集合的最近公共祖先
     * @param root 二叉树的根节点
     * @param nodes 要查找的节点集合
     * @return 最近公共祖先节点
     */
    public static TreeNode findLCA(TreeNode root, Set<TreeNode> nodes) {
        if (root == null || nodes.contains(root)) {
            return root;
        }

        TreeNode left = findLCA(root.left, nodes);
        TreeNode right = findLCA(root.right, nodes);

        // 如果left和right都不为空，则当前节点为最近公共祖先
        if (left != null && right != null) {
            return root;
        }

        // 如果其中一个为空，则返回非空的那个
        return left != null ? left : right;
    }

    public static void main(String[] args) {
        // 构建测试用例的二叉树
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        // 定义要查找的节点集合
        Set<TreeNode> nodes = new HashSet<>(Arrays.asList(root.left, root.right.left));

        // 查找并打印最近公共祖先
        TreeNode lca = findLCA(root, nodes);
        System.out.println("The LCA is: " + lca.val); // 应输出3，因为5和0的最近公共祖先是3
    }

}
