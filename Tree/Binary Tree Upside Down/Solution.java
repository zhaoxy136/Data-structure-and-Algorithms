//Version 0:
//kind of similar with reverse linkedlist with recusive method
class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null) return root;
        TreeNode p = root.left;
        TreeNode newRoot = upsideDownBinaryTree(root.left);
        p.left = root.right;
        p.right = root;
        root.left = null;
        root.right = null;
        return newRoot;
    }
}
