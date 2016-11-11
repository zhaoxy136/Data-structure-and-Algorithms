/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    /**
     * @param head a ListNode
     * @oaram v1 an integer
     * @param v2 an integer
     * @return a new head of singly-linked list
     */
    public ListNode swapNodes(ListNode head, int v1, int v2) {
        // Write your code here
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        ListNode premNode = null;
        ListNode prenNode = null;
        while (head.next != null){
            if (head.next.val == v1){
                premNode = head;
            }
            if (head.next.val == v2){
                prenNode = head;
            }
            head = head.next;
        }
        if (premNode == null || prenNode == null){
            return dummy.next;
        }
        ListNode mNode = premNode.next;
        ListNode nNode = prenNode.next;
        if (mNode.next == nNode){
            premNode.next = nNode;
            mNode.next = nNode.next;
            nNode.next = mNode;
        } else if (nNode.next == mNode){
            prenNode.next = mNode;
            nNode.next = mNode.next;
            mNode.next = nNode;
        } else {
            ListNode temp = mNode.next;
            premNode.next = nNode;
            prenNode.next = mNode;
            mNode.next = nNode.next;
            nNode.next = temp;
        }
        return dummy.next;
    }
}
