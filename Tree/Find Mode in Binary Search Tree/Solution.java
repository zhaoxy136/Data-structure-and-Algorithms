class Solution {
    List<Integer> list = new ArrayList<>();
    int max = 0;
    int count = 1;
    TreeNode prev = null;
    public int[] findMode(TreeNode root) {
        if (root == null) return new int[0];
        helper(root, list);
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
    
    public void helper(TreeNode root, List<Integer> list) {
        if (root == null) return;
        helper(root.left, list);
        if (prev != null) {
            count = root.val == prev.val ? count + 1 : 1;
        }
        if (count > max) {
            max = count;
            list.clear();
            list.add(root.val);
        } else if (count == max) {
            list.add(root.val);
        }
        prev = root;
        helper(root.right, list);
    }
}
