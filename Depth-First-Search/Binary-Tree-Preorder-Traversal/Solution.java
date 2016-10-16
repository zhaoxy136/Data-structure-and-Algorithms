/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 //Version 0: Non-Recursion(Recommend)
 public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode tmp = stack.pop();
            result.add(tmp.val);
            if(tmp.right != null){
                stack.push(tmp.right);
            }
            if(tmp.left != null){
                stack.push(tmp.left);
            }
        }
        return result;
    }
}
 
 //Version 1: Traverse
 public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        helper(root,result);
        return result;
    }
    
    private void helper(TreeNode node, List<Integer> result){
        if(node == null){
            return;
        }
        result.add(node.val);
        helper(node.left,result);
        helper(node.right,result);
    }
}

//Version 2: Divide and Conquer
public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        List<Integer> left = preorderTraversal(root.left);
        List<Integer> right = preorderTraversal(root.right);
        result.add(root.val);
        result.addAll(left);
        result.addAll(right);
        return result;
    }
}
