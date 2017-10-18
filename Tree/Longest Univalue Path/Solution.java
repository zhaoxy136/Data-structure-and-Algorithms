class Solution {
    int res;
    public int longestUnivaluePath(TreeNode root) {
        res = 0;
        helper(root);
        return res;
    }
    public int helper(TreeNode root) {
        if (root == null) return 0;
        int left = helper(root.left);
        int right = helper(root.right);
        int leftSide = 0, rightSide = 0;
        if (root.left != null && root.left.val == root.val) {
            leftSide += 1 + left;
        }
        if (root.right != null && root.right.val == root.val) {
            rightSide += 1 + right;
        }
        res = Math.max(res, leftSide + rightSide);
        return Math.max(leftSide, rightSide);
    }
}
