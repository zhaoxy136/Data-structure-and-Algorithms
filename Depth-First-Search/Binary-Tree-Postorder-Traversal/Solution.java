/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 //Version 0: Iterative
 public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        
        if(root == null){
            return result;
        }
        TreeNode prevNode = null; //previously traversed node
        TreeNode curtNode = root;
        stack.push(root);
        while(!stack.empty()){
            curtNode = stack.peek();
            if(prevNode == null || prevNode.left == curtNode || prevNode.right == curtNode){
                if(curtNode.left != null){
                    stack.push(curtNode.left);
                } else if(curtNode.right != null){
                    stack.push(curtNode.right);
                }
            } else if(curtNode.left == prevNode && curtNode.right != null){
                stack.push(curtNode.right);
            } else{
                stack.pop();
                result.add(curtNode.val);
            }
            prevNode = curtNode;
        }
        return result;
    }
}
 
 //Version 1: Recursive
 public ArrayList<Integer> postorderTraversal(TreeNode root) {
    ArrayList<Integer> result = new ArrayList<Integer>();

    if (root == null) {
        return result;
    }

    result.addAll(postorderTraversal(root.left));
    result.addAll(postorderTraversal(root.right));
    result.add(root.val);

    return result;   
}

 //Version 2: Morris (*)
 //modify preorder and reverse the results
 public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        
        if(root == null){
            return result;
        }
        stack.push(root);
        while(!stack.empty()){
            TreeNode node = stack.pop();
            result.add(node.val);
            if(node.left != null){
                stack.push(node.left);
            }
            if(node.right != null){
                stack.push(node.right);
            }
        }
        Collections.reverse(result);
        return result;
    }
}
