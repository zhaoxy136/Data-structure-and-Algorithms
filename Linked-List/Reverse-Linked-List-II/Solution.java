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
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(m > n || head == null){
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        for(int i = 1; i < m; i++){
            if(head == null){
                return null;
            }
            head = head.next;
        }
        ListNode premNode = head;
        if(head == null){
            return null;
        }
        ListNode mNode = head.next;
        if(mNode == null){
            return null;
        }
        ListNode nNode = mNode, postnNode = nNode.next;
        
        for(int i = m; i < n; i++){
            if(postnNode == null){
                return null;
            }
            ListNode temp = postnNode.next;
            postnNode.next = nNode;
            nNode = postnNode;
            postnNode = temp;
        }
        premNode.next = nNode;
        mNode.next = postnNode;
        return dummy.next;
    }
}

//version 1: more elegant
public class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        for(int i = 1; i < m; i++){
            if(head == null){
                return null;
            }
            head = head.next;
        }
        ListNode mNode = head, nNode = head.next;
        for(int i = m; i < n; i++){
            ListNode temp = mNode.next;
            mNode.next = nNode.next;
            nNode.next = mNode.next.next;
            mNode.next.next = temp;
        }
        return dummy.next;
    }
}
