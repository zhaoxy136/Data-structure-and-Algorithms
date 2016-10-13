/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 Version 0:(Non-Recursion)
 public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null){
            return root;
        }
        TreeNode max = p.val > q.val ? p : q;
        TreeNode min = p.val > q.val ? q : p;
        while(!(max.val >= root.val && min.val <= root.val)){
            if(min.val > root.val){
                root = root.right;
            }
            if(max.val < root.val){
                root = root.left;
            }
            if(root == p || root == q){
                return root;
            }
        }
        return root;
    }
}
