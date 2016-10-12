/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//Version 0: 
public class Solution {
    public int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        int leftMin = minDepth(root.left);
        int rightMin = minDepth(root.right);
        if(leftMin == 0 || rightMin == 0){
            return leftMin + rightMin + 1;
        } else{
            return Math.min(leftMin, rightMin) + 1;
        }
    }
    
}
