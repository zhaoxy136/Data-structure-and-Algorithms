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
public class Solution {
    public int kthSmallest(TreeNode root, int k) {
        TreeNode curr = root;
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            list.add(curr.val);
            curr = curr.right;
        }
        return list.get(k-1);
    }
}

//Version 1: original recursion, but slow...
public class Solution {
    public int kthSmallest(TreeNode root, int k) {
        if (root == null) return 0;
        Map<TreeNode, Integer> map = new HashMap<>();
        buildMap(root, map);
        if (map.getOrDefault(root.left, 0) == k - 1) {
            return root.val;
        }
        if (map.getOrDefault(root.left, 0) > k - 1) {
            return kthSmallest(root.left, k);
        } 
        return kthSmallest(root.right, k - 1 - map.getOrDefault(root.left, 0));
        
    }
    private void buildMap(TreeNode root, Map<TreeNode, Integer> map) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            map.put(root, 1);
            return;
        }
        buildMap(root.left, map);
        buildMap(root.right, map);
        map.put(root, map.getOrDefault(root.left, 0) + map.getOrDefault(root.right, 0) + 1);
    }
}

//Version 2: just little modify, make version 1 much better!
// actually, we don't need hashmap, since the recursion method is simple
public class Solution {
    public int kthSmallest(TreeNode root, int k) {
        if (root == null) return 0;
        int leftNodes = countNodes(root.left);
        if (leftNodes == k - 1) {
            return root.val;
        }
        if (leftNodes > k - 1) {
            return kthSmallest(root.left, k);
        } 
        return kthSmallest(root.right, k - 1 - leftNodes);
    }
    private int countNodes(TreeNode root) {
        if (root == null) return 0;
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
}
