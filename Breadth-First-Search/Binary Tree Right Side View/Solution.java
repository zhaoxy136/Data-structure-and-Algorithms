/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//Version 0: bfs
public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode tmp = queue.poll();
                if (i == size-1) res.add(tmp.val);
                if (tmp.left != null) queue.offer(tmp.left);
                if (tmp.right != null) queue.offer(tmp.right);
            }
        }
        return res;
    }
}

//Version 1: @copyright https://discuss.leetcode.com/topic/11768/my-simple-accepted-solution-java
