//Version 0: recursion
public class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        int left = diameterOfBinaryTree(root.left);
        int right = diameterOfBinaryTree(root.right);
        int path = getHeight(root.left) + getHeight(root.right) + 2;
        return Math.max(Math.max(left, right), path);
    }
    
    public int getHeight(TreeNode root) {
        if (root == null) return -1;
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }
}

//Version 1: use global variable
public class Solution {
    public int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        height(root);
        return max;
    }
    private int height(TreeNode root) {
        if (root == null) return 0;
        int left = height(root.left);
        int right = height(root.right);
        max = Math.max(max, left+right);
        return Math.max(left, right) + 1;
    }
}
