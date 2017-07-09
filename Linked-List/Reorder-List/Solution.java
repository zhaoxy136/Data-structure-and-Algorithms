/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null){
            return;
        }
        ListNode mid = findMiddle(head);
        ListNode list = reverse(mid.next);
        mid.next = null;
        mergeTwo(head, list);
    }
    
    private ListNode findMiddle(ListNode head){
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    
    private ListNode reverse(ListNode head){
        ListNode prev = null;
        while (head != null){
            ListNode tmp = head.next;
            head.next = prev;
            prev = head;
            head = tmp;
        }
        return prev;
    }
    
    private void mergeTwo(ListNode head1, ListNode head2){
        ListNode l1 = head1;
        ListNode tmp = new ListNode(0);
        while (tmp != null){
            tmp = l1.next;
            l1.next = head2;
            l1 = l1.next;
            head2 = tmp;
        }
    }
}

//Version 1:
public class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode middle = findMiddle(head);
        ListNode tail = reverse(middle.next);
        ListNode cur = head;
        while (tail.next != null) {
            ListNode tmp = tail.next;
            tail.next = cur.next;
            cur.next = tail;
            cur = tail.next;
            tail = tmp;
        }
        if (cur == middle) {
            cur.next = tail;
        } else {
            tail.next = cur.next;
            cur.next = tail;
            middle.next = null;
        }
    }
    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
    
    private ListNode findMiddle(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
