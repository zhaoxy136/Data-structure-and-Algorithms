/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
//Version 0 : two pointers
public class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode second = head.next;
        ListNode odd = head;
        ListNode even = second;
        while (odd.next != null && even.next != null){
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = second;
        return head;
    }
}
//Version 1: one pointer
public class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode second = head.next;
        ListNode list = head;
        while (list.next != null && list.next.next != null){
            ListNode tmp = list.next;
            list.next = tmp.next;
            list = list.next;
            tmp.next = list.next;
            tmp = tmp.next;
        }
        list.next = second;
        return head;
    }
}
