//Version 0: half original
public class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int left = findHeight(root.left);
        int right = findHeight(root.right);
            return left == right ? (1 << left) + countNodes(root.right) : (1 << right) + countNodes(root.left);
    }
    private int findHeight(TreeNode root) {
        int height = 0;
        while (root != null) {
            root = root.left;
            height++;
        }
        return height;
    }
}

//Version 1: several amazing solutions
//@copyright https://discuss.leetcode.com/topic/15533/concise-java-solutions-o-log-n-2
