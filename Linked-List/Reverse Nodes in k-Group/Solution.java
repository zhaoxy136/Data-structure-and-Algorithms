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
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        ListNode start = dummy;
        while (head.next != null) {
            for (int i = 0; i < k; i++) {
                if (head.next == null) return dummy.next;
                head = head.next;
            }
            start = reverse(start, head);
            head = start;
        }
        return dummy.next;
    }
    private ListNode reverse(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode cur = head.next;
        ListNode res = head.next;
        while (cur != tail) {
            ListNode tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }
        cur.next = prev;
        head.next = tail;
        return res;
    }
}

//Version 1:
