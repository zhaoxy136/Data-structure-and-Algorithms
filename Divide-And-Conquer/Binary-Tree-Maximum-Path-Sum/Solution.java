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
    private class ResultType{
        int singlePath; // longest path from root to any node
        int maxPath;  // longest path from any node to any node
        ResultType(int singlePath, int maxPath){
            this.singlePath = singlePath;
            this.maxPath = maxPath;
        }    
    }
    
    public int maxPathSum(TreeNode root) {
        if(root == null){
            return 0;
        }
        return helper(root).maxPath;
    }
    
    public ResultType helper(TreeNode root){
        if(root == null){
            return new ResultType(0,Integer.MIN_VALUE);
        }
        
        //Divide
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        //merge
        int singlePath = Math.max(left.singlePath,right.singlePath);
        singlePath = Math.max(singlePath + root.val, 0);
        int maxPath = Math.max(left.maxPath,right.maxPath);
        maxPath = Math.max(maxPath, left.singlePath + right.singlePath + root.val);
        //answer
        return new ResultType(singlePath, maxPath);
    }
}
