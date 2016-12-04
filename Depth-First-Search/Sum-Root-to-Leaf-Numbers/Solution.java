//Version 0: original, but used global variable
public class Solution {
    public int sumNumbers(TreeNode root) {
        helper(new ArrayList<Integer>(), root);
        return count;
    }
    int count = 0;
    private void helper(List<Integer> list, TreeNode root) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            int res = 0;
            for (int i = 0; i < list.size(); i++) {
                res = res * 10 + list.get(i);
            }
            count += res * 10 + root.val;
            return;
        }
        list.add(root.val);
        helper(list, root.left);
        helper(list, root.right);
        list.remove(list.size()-1);
    }
}
//Version 1:
public class Solution {
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        return helper(root, 0);
    }
    private int helper(TreeNode root, int preSum) {
        if (root.left == null && root.right == null) {
            return preSum * 10 + root.val;
        }
        int val = 0;
        if (root.left != null) {
            val += helper(root.left, preSum * 10 + root.val);
        }
        if (root.right != null) {
            val += helper(root.right, preSum * 10 + root.val);
        }
        return val;
    }
}

//Version 2: self modified
public class Solution {
    public int sumNumbers(TreeNode root) {
        return helper(root, 0);
    }
    private int helper(TreeNode root, int preSum) {
        if (root == null) return 0;
        int val = preSum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return val;
        }
        return helper(root.left, val) + helper(root.right, val);
    }
}
