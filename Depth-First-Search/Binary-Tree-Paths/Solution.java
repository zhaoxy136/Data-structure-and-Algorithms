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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        helper(res, String.valueOf(root.val), root);
        return res;
    }
    private void helper(List<String> res, String path, TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            res.add(path);
        }
        if (root.left != null) {
            helper(res, path + "->" + String.valueOf(root.left.val), root.left);
        }
        if (root.right != null) {
            helper(res, path + "->" + String.valueOf(root.right.val), root.right);
        }
    }
}
