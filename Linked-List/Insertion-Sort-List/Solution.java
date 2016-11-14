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

//Version 1: modified
public class Solution {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = head;
        ListNode cur = head.next;
        while (cur != null){
            ListNode tra = dummy;
            while (tra.next.val <= cur.val){
                tra = tra.next;
                if (tra.next.val > cur.val || tra == prev){
                    break;
                }
            }
            if (tra == prev){
                prev = cur;
                cur = cur.next;
            } else {
                ListNode tmp = tra.next;
                tra.next = cur;
                prev.next = cur.next;
                cur.next = tmp;
                cur = prev.next;
                }
            }
        return dummy.next;
    }
}
// Version 2: more efficient 
public class Solution {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = head;
        ListNode cur = head.next;
        while (cur != null){
            if (prev.val > cur.val){
                ListNode tmp = dummy;
                while (tmp.next.val <= cur.val){
                    tmp = tmp.next;
                }
                //ListNode next = cur.next;
                prev.next = cur.next;
                cur.next = tmp.next;
                tmp.next = cur;
                cur = prev.next;
            } else {
                prev = cur;
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
