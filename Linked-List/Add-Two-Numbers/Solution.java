/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head1 = l1;
        ListNode head2 = l2;
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        int carry = 0;
        while (head1 != null || head2 != null || carry != 0){
            int val = carry;
            if (head1 != null){
                val += head1.val;
                head1 = head1.next;
            }
            if (head2 != null){
                val += head2.val;
                head2 = head2.next;
            }
            ListNode tmp = new ListNode(val%10);
            carry = val/10;
            head.next = tmp;
            head = head.next;
        }
        return dummy.next;
    }
}
