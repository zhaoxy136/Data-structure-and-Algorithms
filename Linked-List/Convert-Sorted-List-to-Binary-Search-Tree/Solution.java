/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//Version 0: original
public class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null){
            return null;
        }
        if (head.next == null){
            return new TreeNode(head.val);
        }
        ListNode preMid = findPreMid(head);
        TreeNode root = new TreeNode(preMid.next.val);
        root.right = sortedListToBST(preMid.next.next);
        preMid.next = null;
        root.left = sortedListToBST(head);
        return root;
    }
    
    private ListNode findPreMid(ListNode head){
        ListNode preMid = head;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null){
            preMid = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return preMid;
    }
}

//Version 1: A better one
public class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null){
            return null;
        }
        return helper(head, null);
    }
    
    private TreeNode helper(ListNode head, ListNode tail){
        if (head == tail){
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != tail && fast.next != tail){
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = helper(head, slow);
        root.right = helper(slow.next, tail);
        return root;
    }
}
