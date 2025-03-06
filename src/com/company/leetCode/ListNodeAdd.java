package com.company.leetCode;

/**
 * 两数相加
 * @author suhe17
 * @since 2025/3/6
 */
public class ListNodeAdd {

    static int v = 0;
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null && v == 0) return null;
        int v1 = 0;
        int v2 = 0;
        if (l1 != null) v1 = l1.val;
        if (l2 != null) v2 = l2.val;
        int val = v1 + v2 + v;
        ListNode node = null;
        node = new ListNode(val % 10);
        v = val / 10;
        node.next = addTwoNumbers(l1 != null ? l1.next : null, l2 != null ? l2.next : null);
        return node;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode();
        l1.val = 0;
        ListNode l2 = new ListNode();
        l2.val = 0;
        ListNode res = addTwoNumbers(l1,l2);
        System.out.println(res);
    }
}
