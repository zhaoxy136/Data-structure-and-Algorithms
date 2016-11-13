/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
//Version 0: original O(n2)
public class Solution {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode prev = head;
        ListNode cur = head.next;
        while (cur != null){
            if (cur.val < prev.val){
                head = insertion(head, cur);
                cur = prev.next;
            } else {
                cur = cur.next;
                prev = prev.next;
            }
        }
        return head;
    }
    
    private ListNode insertion(ListNode head, ListNode p){
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur = head;
        while (cur.val <= p.val){
            prev = cur;
            cur = cur.next;
            if (cur == p){
                return head;
            }
        }
        if (cur.next == p){
            prev.next = p;
            cur.next = p.next;
            p.next = cur;
        } else {
            ListNode tmp = cur;
            while (tmp.next != p){
                tmp = tmp.next;
            }
            prev.next = p;
            tmp.next = p.next;
            p.next = cur;
        }
        return dummy.next;
    }
}

//Version 1: 
