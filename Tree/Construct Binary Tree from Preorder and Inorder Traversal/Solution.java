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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(preorder, inorder, 0, 0, inorder.length-1);
    }
    private TreeNode helper(int[] preorder, int[] inorder, int root, int start, int end) {
        if (start > end) return null;
        if (start == end) return new TreeNode(inorder[start]);
        TreeNode node = new TreeNode(preorder[root]);
        int index = findRoot(preorder[root], inorder, start, end);
        TreeNode left = helper(preorder, inorder, root+1, start, index-1);
        TreeNode right = helper(preorder, inorder, root+1+index-start, index+1, end);
        node.left = left;
        node.right = right;
        return node;
    }
    private int findRoot(int val, int[] nums, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (nums[i] == val) {
                return i;
            }
        }
        return 0;
    }
}

//Version 1: using map to store the index
//@copyright https://discuss.leetcode.com/topic/29838/5ms-java-clean-solution-with-caching
public TreeNode buildTree(int[] preorder, int[] inorder) {
    Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();
    
    for(int i = 0; i < inorder.length; i++) {
        inMap.put(inorder[i], i);
    }

    TreeNode root = buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    return root;
}

public TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
    if(preStart > preEnd || inStart > inEnd) return null;
    
    TreeNode root = new TreeNode(preorder[preStart]);
    int inRoot = inMap.get(root.val);
    int numsLeft = inRoot - inStart;
    
    root.left = buildTree(preorder, preStart + 1, preStart + numsLeft, inorder, inStart, inRoot - 1, inMap);
    root.right = buildTree(preorder, preStart + numsLeft + 1, preEnd, inorder, inRoot + 1, inEnd, inMap);
    
    return root;
}
