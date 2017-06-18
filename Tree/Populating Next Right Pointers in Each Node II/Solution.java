//Version 0:
/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        TreeLinkNode nextLeftmost = null;//head of next level
        TreeLinkNode prev = null;//leading node of next level!
        TreeLinkNode cur = root;
        while (cur != null) {
            //iterate the current level, meaning connect the next level
            while (cur != null) {
                if (cur.left != null) {
                    if (prev == null) {
                        nextLeftmost = cur.left;
                    } else {
                        prev.next = cur.left;
                    }
                    prev = cur.left;
                }
                if (cur.right != null) {
                    if (prev == null) {
                        nextLeftmost = cur.right;
                    } else {
                        prev.next = cur.right;
                    }
                    //move to next node
                    prev = cur.right;
                }
                cur = cur.next;
            }
            //move to next level
            cur = nextLeftmost;
            nextLeftmost = null;
            prev = null;
        }
    }
}

//Version 1: using dummy node. SMART!!
public class Solution {
    public void connect(TreeLinkNode root) {
        TreeLinkNode cur = root;
        TreeLinkNode head = new TreeLinkNode(0);
        TreeLinkNode prev = head;
        while (cur != null) {
            if (cur.left != null) {
                prev.next = cur.left;
                prev = prev.next;
            }
            if (cur.right != null) {
                prev.next = cur.right;
                prev = prev.next;
            }
            cur = cur.next;
            if (cur == null) {
                cur = head.next;
                head.next = null;
                prev = head;
            }
        }
    }
}
