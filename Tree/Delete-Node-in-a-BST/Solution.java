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
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode cur = root;
        TreeNode prev = root;
        while (cur != null) {
            if (cur.val == key) break;
            prev = cur;
            if (cur.val < key) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        if (cur == null) return root;
        if (cur.left == null) {
            if (prev == cur) return cur.right;
            if (prev.val > cur.val) {
                prev.left = cur.right;
            } else {
                prev.right = cur.right;
            }
        } else if (cur.right == null) {
            if (prev == cur) return cur.left;
            if (prev.val > cur.val) {
                prev.left = cur.left;
            } else {
                prev.right = cur.left;
            }
        } else {
            TreeNode successor = findRightMin(cur);
            int tmp = successor.val;
            successor = deleteNode(cur, tmp);
            cur.val = tmp;
        }
        return root;
    }
    private TreeNode findRightMin(TreeNode node) {
        TreeNode successor = node.right;
        while (true) {
            if (successor.left == null) break;
            successor = successor.left;
        }
        return successor;
    }
}

//Version 1: brilliant! @copyright https://discuss.leetcode.com/topic/65792/recursive-easy-to-understand-java-solution
public class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            TreeNode successor = findRightMin(root);
            root.val = successor.val;
            root.right = deleteNode(root.right, root.val);
        }
        return root;
    }
    private TreeNode findRightMin(TreeNode node) {
        node = node.right;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
