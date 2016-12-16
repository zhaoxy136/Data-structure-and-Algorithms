/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//Version 0: Recursion
public class Solution {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<TreeNode>();
        return helper(1, n);
    }
    private List<TreeNode> helper(int start, int end) {
        List<TreeNode> res = new ArrayList<>();
        if (start > end) {
            res.add(null);
            return res;
        }
        if (start == end) {
            res.add(new TreeNode(start));
            return res;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> left = helper(start, i-1);
            List<TreeNode> right = helper(i+1, end);
            for (TreeNode lnode : left) {
                for (TreeNode rnode : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = lnode;
                    root.right = rnode;
                    res.add(root);
                }
            }
        }
        return res;
    }
}

//Version 1: DP
public class Solution {
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode>[] res = new List[n+1];
        res[0] = new ArrayList<>();
        if (n == 0) return res[0];
        res[0].add(null); // can't be removed, or the following for loop will be skip, since res[0] has nothing!
        for (int i = 1; i <= n; i++) {
            res[i] = new ArrayList<>();
            for (int j = 1; j <= i; j++) { // j is the current root value
                for (TreeNode lnode : res[j-1]) {
                    for (TreeNode rnode : res[i-j]) {
                        TreeNode root = new TreeNode(j);
                        root.left = lnode;
                        root.right = clone(rnode,j);
                        res[i].add(root);
                    }
                }
            }
        }
        return res[n];
    }
    private TreeNode clone(TreeNode root, int offset) {
        if (root == null) return null;
        TreeNode node = new TreeNode(root.val + offset);
        node.left = clone(root.left, offset);
        node.right = clone(root.right, offset);
        return node;
    }
}
