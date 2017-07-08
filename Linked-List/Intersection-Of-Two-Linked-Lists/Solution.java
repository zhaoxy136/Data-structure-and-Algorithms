/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
//Version 0:
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = getLen(headA);
        int lenB = getLen(headB);
        ListNode longHead = lenA > lenB ? headA : headB;
        ListNode shortHead = lenA > lenB ? headB : headA;
        for (int i = 0; i < Math.abs(lenA - lenB); i++) {
            longHead = longHead.next;
        }
        while (shortHead != null) {
            if (shortHead == longHead) return shortHead;
            shortHead = shortHead.next;
            longHead = longHead.next;
        }
        return null;
    }
    
    private int getLen(ListNode head) {
        int len = 0;
        while (head != null) {
            head = head.next;
            len++;
        }
        return len;
    }
}

//Version 1:
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null){
            return null;
        }
        ListNode p = headA;
        ListNode q = headB;
        while (p != null && q != null && p != q){
            p = p.next;
            q = q.next;
            if (p == null && q == null){
                return null;
            }
            if (p == null){
                p = headB;
            }
            if (q == null){
                q = headA;
            }
        }
        return p;
    }
}
