//Version 0: original
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    private int min = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        if (root.left != null) {
            int left = getMax(root.left);
            min = Math.min(min, root.val-left);
        }
        if (root.right != null) {
            int right = getMin(root.right);
            min = Math.min(min, right-root.val);
        }
        return min;
    }
    private int getMin(TreeNode node) {
        if (node.left == null && node.right == null) {
            return node.val;
        }
        if (node.right != null) {
            int right = getMin(node.right);
            min = Math.min(min, right-node.val);
        }
        if (node.left != null) {
            int left = getMax(node.left);
            min = Math.min(min, node.val-left);
            return getMin(node.left);
        }
        return node.val;
        
    }
    private int getMax(TreeNode node) {
        if (node.left == null && node.right == null) {
            return node.val;
        }
        if (node.left != null) {
            int left = getMax(node.left);
            min = Math.min(min, node.val-left);
        }
        if (node.right != null) {
            int right = getMin(node.right);
            min = Math.min(min, right-node.val);
            return getMax(node.right);
        }
        return node.val;
    }
}

//Version 1:
public class Solution {
    class T {
        int minDif;
        int min;
        int max;
        T (int minDif, int min, int max) {
            this.minDif = minDif;
            this.min = min;
            this.max = max;
        }
    }
    public int getMinimumDifference(TreeNode root) {
        return helper(root).minDif;
    }
    
    private T helper(TreeNode node) {
        if (node.left == null && node.right == null) {
            return new T(Integer.MAX_VALUE, node.val, node.val);
        }
        int minDif = Integer.MAX_VALUE;
        int min = node.val;
        int max = node.val;
        if (node.left != null) {
            T left = helper(node.left);
            min = left.min;
            minDif = Math.min(left.minDif, node.val-left.max);
        }
        if (node.right != null) {
            T right = helper(node.right);
            max = right.max;
            minDif = Math.min(minDif, Math.min(right.minDif, right.min-node.val));
        }
        return new T(minDif, min, max);
    }
}

//Version 2: inorder traversal
public class Solution {
    int min = Integer.MAX_VALUE;
    TreeNode prev = null;
    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return min;
    }
    private void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        if (prev != null) {
            min = Math.min(min, node.val - prev.val);
        }
        prev = node;
        inorder(node.right);
    }
}

//Version 3: TreeSet
//@copyright: https://discuss.leetcode.com/topic/80823/two-solutions-in-order-traversal-and-a-more-general-way-using-treeset/2
public class Solution {
    TreeSet<Integer> set = new TreeSet<>();
    int min = Integer.MAX_VALUE;
    
    public int getMinimumDifference(TreeNode root) {
        if (root == null) return min;
        
        if (!set.isEmpty()) {
            if (set.floor(root.val) != null) {
                min = Math.min(min, root.val - set.floor(root.val));
            }
            if (set.ceiling(root.val) != null) {
                min = Math.min(min, set.ceiling(root.val) - root.val);
            }
        }
        
        set.add(root.val);
        
        getMinimumDifference(root.left);
        getMinimumDifference(root.right);
        
        return min;
    }
}
