/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//Version 0: (Recursion)
public class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null){
            return false;
        }
        if(root.left == null && root.right == null){
            return sum == root.val;
        }
        
    //Divide     
        boolean left = hasPathSum(root.left, sum-root.val);
        boolean right = hasPathSum(root.right, sum-root.val);
    //Conquer
        return (left || right);
    }
}

