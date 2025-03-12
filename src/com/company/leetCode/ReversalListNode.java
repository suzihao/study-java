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

    public static ListNode reverseList1(ListNode head) {
        ListNode prev = null; // 前一个节点初始化为null
        ListNode current = head; // 当前节点初始化为头节点
        ListNode nextNode = null; // 下一个节点初始化为null

        while (current != null) {
            nextNode = current.next; // 保存当前节点的下一个节点
            current.next = prev; // 将当前节点的next指向前一个节点
            prev = current; // 更新前一个节点为当前节点
            current = nextNode; // 移动到下一个节点
        }

        return prev; // 返回新的头节点，即原链表的最后一个节点
    }

    // 辅助函数：打印链表
    public static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // 创建测试链表: 1->2->3->4->5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.println("Original list:");
        printList(head);

        // 反转链表
        ListNode reversedHead = reverseList1(head);

        System.out.println("Reversed list:");
        printList(reversedHead);
    }
}
