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
