public class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return helper(postorder, map, 0, inorder.length-1, postorder.length-1);
    }
    private TreeNode helper(int[] postorder, Map<Integer, Integer> map, int start, int end, int root) {
        if (start > end) return null;
        TreeNode node = new TreeNode(postorder[root]);
        int index = map.get(postorder[root]);
        node.left = helper(postorder, map, start, index-1, root+index-end-1);
        node.right = helper(postorder, map, index+1, end, root-1);
        return node;
    }
}
