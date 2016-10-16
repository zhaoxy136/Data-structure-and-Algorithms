/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 //Version 1: Divide-And-Conquer
public class Solution {
    private class ResultType{
        boolean isBST;
        int maxVal;
        int minVal;
        ResultType(boolean isBST, int maxVal, int minVal){
            this.isBST = isBST;
            this.maxVal = maxVal;
            this.minVal = minVal;
        }
    }
    
    public boolean isValidBST(TreeNode root) {
        return helper(root).isBST;
    }
    
    private ResultType helper(TreeNode node){
        if(node == null){
            return new ResultType(true, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        ResultType left = helper(node.left);
        ResultType right = helper(node.right);
        //Note that we added node.left/right != null to in case the extreme scenario
        if(node.left != null && left.maxVal >= node.val || node.right != null && right.minVal <= node.val){
            return new ResultType(false, 0, 0);
        }
        if(!left.isBST || !right.isBST){
            return new ResultType(false, 0, 0);
        }
        return new ResultType(true, Math.max(right.maxVal,node.val), Math.min(left.minVal, node.val));
    }
}
