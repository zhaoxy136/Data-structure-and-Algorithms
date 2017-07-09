/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
//Version 0:
public class Solution {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode newHead = new ListNode(0);
        ListNode mid = new ListNode(0);
        ListNode left = newHead;
        ListNode right = mid;
        while (head != null){
            if (head.val < x){
                left.next = new ListNode(head.val);
                left = left.next;
            } else {
                right.next = new ListNode(head.val);
                right = right.next;
            }
            head = head.next;
        }
        left.next = mid.next;
        return newHead.next;
    }
}

//Version 1: in place
public class Solution {
    public ListNode partition(ListNode head, int x) {
        if (head == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        while (head.next != null && head.next.val < x) {
            head = head.next;
        }
        ListNode partition = head;
        ListNode prev = head.next;
        head = prev;
        while (head != null) {
            if (head.val < x) {
                prev.next = head.next;
                head.next = partition.next;
                partition.next = head;
                head = prev.next;
                partition = partition.next;
            } else {
                prev = head;
                head = head.next;
            }
        }
        return dummy.next;
    }
}

//Version 2:
public class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode node1 = new ListNode(0);
        ListNode node2 = new ListNode(0);
        ListNode l1 = node1;
        ListNode l2 = node2;
        while (head != null) {
            if (head.val < x) {
                l1.next = head;
                l1 = l1.next;
            } else {
                l2.next = head;
                l2 = l2.next;
            }
            head = head.next;
        }
        l2.next = null;
        l1.next = node2.next;
        return node1.next;
    }
}
