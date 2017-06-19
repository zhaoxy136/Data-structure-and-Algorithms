LeetCode: https://leetcode.com/problems/binary-tree-preorder-traversal/#/description

//Version 0: non-recursive
public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                res.add(p.val);
                stack.push(p);
                p = p.left;
            }
            p = stack.pop();
            p = p.right;
        }
        return res;
    }
}

//Version 1: recursive
public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }
    private void helper(TreeNode node, List<Integer> res) {
        if (node == null) return;
        res.add(node.val);
        helper(node.left, res);
        helper(node.right, res);
    }
}
