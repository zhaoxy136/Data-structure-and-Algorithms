//Version 0:
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
    public String tree2str(TreeNode t) {
        if (t == null) return "";
        StringBuilder res = new StringBuilder();
        res.append(t.val);
        if (t.left == null && t.right == null) return res.toString();
        if (t.left != null) res.append(helper(t.left));
        else res.append("()");
        if (t.right != null) res.append(helper(t.right));
        return res.toString();
    }
    
    private String helper(TreeNode node) {
        StringBuilder res = new StringBuilder();
        res.append('(').append(node.val);
        if (node.left == null && node.right == null) return res.append(')').toString();
        if (node.left != null) {
            String leftStr = helper(node.left);
            res.append(leftStr);
        } else res.append("()");
        if (node.right != null) {
            String rightStr = helper(node.right);
            res.append(rightStr);
        }
        return res.append(')').toString();
    }
}

//Version 1:
public class Solution {
    public String tree2str(TreeNode t) {
        if (t == null) return "";
        StringBuilder res = new StringBuilder();
        res.append(t.val);
        if (t.left == null && t.right == null) {
            return res.toString();
        }
        if (t.right == null) {
            res.append('(').append(tree2str(t.left)).append(')');
        } else {
            res.append('(').append(tree2str(t.left)).append(")(").append(tree2str(t.right)).append(')');
        }
        return res.toString();
    }
}
