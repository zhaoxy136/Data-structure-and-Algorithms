//Version 0:
public class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        //find middle node
        ListNode middle = findMiddle(head);
        
        ListNode next = sortList(middle.next);
        middle.next = null;
        head = sortList(head);
        
        //merge two lists together
        return mergeTwoLists(head, next);
    }
    
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                node.next = l1;
                l1 = l1.next;
            } else {
                node.next = l2;
                l2 = l2.next;
            }
            node = node.next;
        }
        if (l1 == null) {
            node.next = l2;
        } else {
            node.next = l1;
        }
        return dummy.next;
    }
    
    private ListNode findMiddle(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
