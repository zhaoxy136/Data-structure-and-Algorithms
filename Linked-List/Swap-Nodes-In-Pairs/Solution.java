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
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = head;
        ListNode prev = dummy;
        while(cur != null && cur.next != null){
            ListNode temp = cur.next;
            prev.next = temp;
            cur.next = temp.next;
            temp.next = cur;
            prev = cur;
            cur = cur.next;
        }
        return dummy.next;
    }
}

//Version 1: Recursion
public class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode newHead = head.next;
        ListNode tmp = head.next.next;
        newHead.next = head;
        head.next = swapPairs(tmp);
        return newHead;
    }
}
