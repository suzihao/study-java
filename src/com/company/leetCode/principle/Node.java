package com.company.leetCode.principle;

import java.util.List;

/**
 * 多叉树
 * @author suhe17
 * @since 2025/3/8
 */
public class Node {
    int val;
    List<Node> children;

    public static void traverse(Node node){
        if (node == null){
            return;
        }
        System.out.println(node.val);
        for (Node child : node.children) {
            traverse(child);
        }
    }

    public static void main(String[] args) {

    }
}
