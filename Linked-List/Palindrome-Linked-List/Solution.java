public class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null){
            return true;
        }
        ListNode middle = findMiddle(head);
        middle.next = reverse(middle.next);
        while (middle.next != null){
            if(head.val != middle.next.val){
                return false;
            }
            head = head.next;
            middle = middle.next;
        }
        return true;
    }
    
    private ListNode findMiddle(ListNode head){
        if (head == null){
            return null;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    
    private ListNode reverse(ListNode head){
        ListNode prevNode = null;
        while (head != null){
            ListNode tmp = head.next;
            head.next = prevNode;
            prevNode = head;
            head = tmp;
        }
        return prevNode;
    }
    
}
