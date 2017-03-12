//Version 0:
public class Solution {
    public int findBottomLeftValue(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res, 0);
        return res.get(res.size()-1);
    }
    private void dfs(TreeNode node, List<Integer> res, int depth) {
        if (node == null) return;
        if (depth == res.size()) {
            res.add(node.val);
        }
        dfs(node.left, res, depth+1);
        dfs(node.right, res, depth+1);
    }
}

//Version 1: similar to levelorder traversal
public class Solution {
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int left = root.val;
        while (!queue.isEmpty()) {
            int size = queue.size();
            left = queue.peek().val;
            while(size-- > 0) {
                TreeNode tmp = queue.poll();
                if (tmp.left != null) queue.add(tmp.left);
                if (tmp.right != null) queue.add(tmp.right);
            }
        }
        return left;
    }
}

//Version 2: just level order travelsal, return the last node's value
public int findLeftMostNode(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
        root = queue.poll();
        if (root.right != null)
            queue.add(root.right);
        if (root.left != null)
            queue.add(root.left);
    }
    return root.val;
}
