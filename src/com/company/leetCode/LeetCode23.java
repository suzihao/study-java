package com.company.leetCode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author 苏和
 * @version 2020/4/26
 */
public class LeetCode23 {

      public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }

        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));

        for (ListNode list : lists) {
            if (list == null) {
                continue;
            }
            pq.add(list);
        }

        while (!pq.isEmpty()) {
            ListNode nextNode = pq.poll();
            curr.next = nextNode;
            curr = curr.next;
            if (nextNode.next != null) {
                pq.add(nextNode.next);
            }
        }
        return dummyHead.next;
    }


    public static ListNode mergeKLists2(ListNode[] lists) {
          if (lists.length == 0){
              return null;
          }
          int k = lists.length;
          while (k > 1){
              int idx = 0;
              for(int i = 0;i<k;i +=2){
                  if (i == k-1){
                      lists[idx++] = lists[i];
                  }else {
                      lists[idx++] = mergeTwoLists(lists[i], lists[i + 1]);
                  }
              }
              k = idx;
          }

          return lists[0];
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        while(list1 != null && list2 != null){
            if(list1.val < list2.val){
                tail.next = list1;
                list1 = list1.next;
            }else{
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }
        tail.next = list1 == null? list2: list1;

        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);
        ListNode[] lists = {l1,l2,l3};

        mergeKLists2(lists);
    }

}
