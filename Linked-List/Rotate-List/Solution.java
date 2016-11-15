/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null){
            return null;
        }
        ListNode fast = head;
        int len = 0;
        while (fast != null){
            len++;
            fast = fast.next;
        }
        k = k % len;
        fast = head;
        for (int i = 0; i < k; i++){
            fast = fast.next;
        }
        ListNode slow = head;
        while (fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }
        fast.next = head;
        ListNode newHead = slow.next;
        slow.next = null;
        return newHead;
    }
}
