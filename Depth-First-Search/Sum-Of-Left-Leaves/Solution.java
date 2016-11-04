/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//version 0: original, recursive
public class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null){
            return 0;
        }
        int leftSum = 0;
        if(root.left != null){
            if(root.left.left == null && root.left.right == null){
                return root.left.val + sumOfLeftLeaves(root.right);
            }
            leftSum = sumOfLeftLeaves(root.left);
        }
        leftSum += sumOfLeftLeaves(root.right);
        return leftSum
    }
}

//Version 1: iterative method, using stack
public class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null){
            return 0;
        }
        int leftSum = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            if(node.left != null){
                if(node.left.left == null && node.left.right == null){
                    leftSum += node.left.val;
                }else{
                    stack.push(node.left);
                }
            }
            if(node.right != null){
                if(node.right.left != null || node.right.right != null){
                    stack.push(node.right);
                }
            }
        }
        return leftSum;
    }
}

//Version 2: using left indicator, recursive
public class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        return helper(root,false);
    }
    
    public int helper(TreeNode node, boolean isLeft){
        if(node == null){
            return 0;
        }
        if(node.left == null && node.right == null && isLeft){
            return node.val;
        }else{
            return helper(node.left,true) + helper(node.right,false);
        }
    }
}

