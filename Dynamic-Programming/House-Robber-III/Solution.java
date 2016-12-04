// Very nice explanation: https://discuss.leetcode.com/topic/39834/step-by-step-tackling-of-the-problem
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//Version 0: original
public class Solution {
    public int rob(TreeNode root) {
        if (root == null) return 0;
        return Math.max(robbed(root), notRobbed(root));
    }
    private int robbed(TreeNode root) {
        if (root == null) return 0;
        int left = notRobbed(root.left);
        int right = notRobbed(root.right);
        return root.val + left + right;
    }
    private int notRobbed(TreeNode root) {
        if (root == null) return 0;
        int left = rob(root.left);
        int right = rob(root.right);
        return left + right;
    }
}

//Version 1: DP bottom up
public class Solution {
    public int rob(TreeNode root) {
        int[] res = helper(root);
        return Math.max(res[0], res[1]);
    }
    
    private int[] helper(TreeNode root) {
        if (root == null) {
            return new int[2];
        }
        int[] left = helper(root.left);
        int[] right = helper(root.right);
        return new int[]{Math.max(left[0], left[1]) + Math.max(right[0], right[1]), root.val + left[0] + right[0]};
    }
}
