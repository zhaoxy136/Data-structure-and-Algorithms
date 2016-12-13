/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
//Version 0: level order traversal
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null) return;
        Queue<TreeLinkNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeLinkNode prev = queue.poll();
            if (prev.left != null) queue.offer(prev.left);
            if (prev.right != null) queue.offer(prev.right);
            for (int i = 1; i < size; i++) {
                TreeLinkNode curr = queue.poll();
                prev.next = curr;
                prev = prev.next;
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
        }
    }
}

//Version 1: without using queue
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null) return;
        TreeLinkNode curr = root;
        TreeLinkNode nextLevel = null;
        while (curr.left != null) {
            nextLevel = curr.left;
            while (curr != null) {
                curr.left.next = curr.right;
                curr.right.next = curr.next == null ? null : curr.next.left;
                curr = curr.next;
            }
            curr = nextLevel;
        }
    }
}
