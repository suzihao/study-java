package com.company.leetCode;

/**
 * 反转链表
 * @author suhe17
 * @since 2025/3/6
 */
public class ReversalListNode {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head; // 空链表或只有一个节点的链表不需要反转，直接返回头节点
        }
        ListNode newHead = reverseList(head.next); // 递归到链表的末尾，然后开始反转
        head.next.next = head; // 反转当前节点的指向，指向原来的前一个节点（现在是下一个节点）
        head.next = null; // 最后一个节点的前一个节点指向null，完成反转链表的操作
        return newHead; // 返回新的头节点，即原链表的尾节点
    }

    public static void main(String[] args) {

    }
}
