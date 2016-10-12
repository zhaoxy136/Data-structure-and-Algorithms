/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 Version 0: Traversal
 public class Solution {
 int depth;
 public int maxDepth(TreeNode root) {
    depth = 0;
    if(root == null){
        return 0;
    }
    helper(root,1);
    return depth;
 }
//Helper Method
 private void helper(TreeNode node, int curtDepth){
        if(node == null){
            return;
        }
        if(curtDepth > depth){
            depth = curtDepth;
        }
        helper(node.left, curtDepth + 1);
        helper(node.right,curtDepth + 1);
    }
}

 Version 1:Divide And Conquer
 
 public class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        int depth;
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        depth = Math.max(leftDepth, rightDepth) + 1;
        return depth;
    }
}
