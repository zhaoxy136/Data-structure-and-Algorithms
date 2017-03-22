Version 0: iterative
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode convertBST(TreeNode root) {
        if (root == null) return null;
        Stack<TreeNode> stack = new Stack<>();
        int add = 0;
        TreeNode prev = root;
        stack.push(prev);
        while (!stack.isEmpty()) {
            
            while (prev.right != null) {
                stack.push(prev.right);
                prev = prev.right;
            }
            TreeNode tmp = stack.pop();
            tmp.val += add;
            add = tmp.val;
            if (tmp.left != null) {
                stack.push(tmp.left);
                prev = tmp.left;
            }
        }
        return root;
    }
}

Version 1: Recursive
public class Solution {
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        helper(root);
        return root;
    }
    
    private void helper(TreeNode node) {
        if (node == null) return;
        helper(node.right);
        node.val += sum;
        sum = node.val;
        helper(node.left);
    }
}
