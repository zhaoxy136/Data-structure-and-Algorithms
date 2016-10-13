/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class ResultType{
    public boolean isBalanced;
    public int depth;
    public ResultType(boolean isBalanced, int depth){
        this.isBalanced = isBalanced;
        this.depth = depth;
    }
} 

public class Solution {
    public boolean isBalanced(TreeNode root) {
        ResultType result = helper(root);
        return result.isBalanced;
    }
    
    private ResultType helper(TreeNode node){
        if(node == null){
            return new ResultType(true, 0);
        }
        ResultType left = helper(node.left);
        ResultType right = helper(node.right);
        //Subtree not balanced
        if(!left.isBalanced || !right.isBalanced){
            return new ResultType(false, -1);
        }
        //root not balanced
        if(Math.abs(left.depth - right.depth) > 1){
            return new ResultType(false, -1);
        } 
        return new ResultType(true, Math.max(left.depth, right.depth) + 1);
    }
    
}
