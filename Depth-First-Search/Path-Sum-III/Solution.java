/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//Version 0:
// @copyright https://discuss.leetcode.com/topic/64388/simple-ac-java-solution-dfs
public class Solution {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return helper(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    private int helper(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int count = root.val == sum ? 1 : 0;
        count += helper(root.left, sum - root.val);
        count += helper(root.right, sum - root.val);
        return count;
    }
}
//Version 1: 
// @copyright https://discuss.leetcode.com/topic/64526/17-ms-o-n-java-prefix-sum-method
public class Solution {
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        return helper(map, root, 0, sum);
    }
    private int helper(Map<Integer, Integer> map, TreeNode root, int preSum, int target) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        preSum += root.val;
        if (map.containsKey(preSum - target)) {
            count += map.get(preSum - target);
        }
        int tmp = map.getOrDefault(preSum, 0);
        map.put(preSum, tmp+1);
        count += helper(map, root.left, preSum, target);
        count += helper(map, root.right, preSum, target);
        map.put(preSum, map.get(preSum)-1);
        return count;
    }
}
