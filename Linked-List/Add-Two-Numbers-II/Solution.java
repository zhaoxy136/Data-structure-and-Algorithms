//Version 0 : Reverse and calculate
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
        if (l1 == null || l2 == null){
            return l1 == null ? l2 : l1;
        }
        l1 = reverse(l1);
        l2 = reverse(l2);
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0){
            int val = carry;
            if (l1 != null){
                val += l1.val;
                l1 = l1.next;
            }
            if (l2 != null){
                val += l2.val;
                l2 = l2.next;
            }
            carry = val / 10;
            head.next = new ListNode(val % 10);
            head = head.next;
        }
        return reverse(dummy.next);
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
}

//Version 1: Two Stacks
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null){
            return l1 == null ? l2 : l1;
        }
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        
        while (l1 != null){
            stack1.push(l1);
            l1 = l1.next;
        }
        while (l2 != null){
            stack2.push(l2);
            l2 = l2.next;
        }
        ListNode list = new ListNode(0);
        int carry = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0){
            if (!stack1.isEmpty()){
                carry += stack1.pop().val;
            }
            if (!stack2.isEmpty()){
                carry += stack2.pop().val;
            }
            list.val = carry % 10;
            carry /= 10;
            ListNode head = new ListNode(carry);
            head.next = list;
            list = head;
        }
        return list.val == 0 ? list.next : list;
    }
}
